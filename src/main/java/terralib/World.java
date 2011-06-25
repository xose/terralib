/**
 * Copyright 2011 José Martínez
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package terralib;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import terralib.enums.BlockType;
import terralib.util.ByteBufferInputStream;
import terralib.util.CSharpData;

import com.google.common.io.Files;
import com.google.common.io.LittleEndianDataInputStream;
import com.google.common.io.LittleEndianDataOutputStream;

public final class World {

	private final int VERSION = 12;

	private final ByteBuffer buffer;
	private final DataInput input;

	private final WorldInfo info;

	private final int[] tileBufferPos;
	private final SortedMap<Integer, Tile> modifiedTiles = new TreeMap<Integer, Tile>();

	private final Map<Position, Chest> chests = new HashMap<Position, Chest>();
	private final Map<Position, String> signs = new HashMap<Position, String>();
	private final Map<Position, NPC> npcs = new HashMap<Position, NPC>();

	public World(File file) throws IOException {
		buffer = Files.map(file);
		input = new LittleEndianDataInputStream(new ByteBufferInputStream(buffer));

		// Header
		if (input.readInt() != VERSION)
			throw new IOException("World version mismatch");

		info = new WorldInfo(input);

		// Tiles
		tileBufferPos = new int[info.getWidth()];
		for (int i = 0; i < info.getWidth(); i++) {
			tileBufferPos[i] = buffer.position();
			skipTiles(input, info.getHeight());
		}

		// Chests
		for (int i = 0; i < 1000; i++) {
			if (input.readBoolean() == false) {
				continue;
			}

			Position pos = new Position(input);
			Chest chest = new Chest(input);

			chests.put(pos, chest);
		}

		// Signs
		for (int i = 0; i < 1000; i++) {
			if (input.readBoolean() == false) {
				continue;
			}

			String text = CSharpData.readString(input);
			Position pos = new Position(input);

			signs.put(pos, text);
		}

		// NPCs
		while (input.readBoolean() == true) {
			NPC npc = new NPC(input);
			Position pos = new Position(input);

			npcs.put(pos, npc);
		}
	}

	public final void write(File file) throws IOException {
		OutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
		DataOutput output = new LittleEndianDataOutputStream(stream);

		// Header
		output.writeInt(VERSION);
		info.write(output);

		// Tiles
		buffer.position(tileBufferPos[0]); // Reset buffer

		int nextMod = modifiedTiles.size() > 0 ? modifiedTiles.firstKey().intValue() : -1;
		for (int i = 0; i < info.getHeight() * info.getWidth(); i++) {
			if (nextMod == i) {
				modifiedTiles.get(Integer.valueOf(i)).write(output);
				modifiedTiles.remove(Integer.valueOf(i));
				nextMod = modifiedTiles.firstKey().intValue();

				skipTiles(input, 1);
				continue;
			}

			new Tile(input).write(output);
		}

		// Chests
		for (Entry<Position, Chest> entry : chests.entrySet()) {
			output.writeBoolean(true);

			entry.getKey().write(output);
			entry.getValue().write(output);
		}

		for (int i = 0; i < 1000 - chests.size(); i++) {
			output.writeBoolean(false);
		}

		// Signs
		for (Entry<Position, String> entry : signs.entrySet()) {
			output.writeBoolean(true);

			CSharpData.writeString(output, entry.getValue());
			entry.getKey().write(output);
		}

		for (int i = 0; i < 1000 - signs.size(); i++) {
			output.writeBoolean(false);
		}

		// NPCs
		for (Entry<Position, NPC> entry : npcs.entrySet()) {
			output.writeBoolean(true);

			entry.getValue().write(output);
			entry.getKey().write(output);
		}

		output.writeBoolean(false);

		// Done
		stream.flush();
		stream.close();
	}

	private final void skipTiles(DataInput input, int numTiles) throws IOException {
		for (int i = 0; i < numTiles; i++) {
			if (input.readBoolean()) {
				BlockType blockType = BlockType.fromID(input.readUnsignedByte());

				if (blockType.hasExtra()) {
					input.skipBytes(4);
				}
			}

			input.skipBytes(1);

			if (input.readBoolean()) {
				input.skipBytes(1);
			}

			if (input.readBoolean()) {
				input.skipBytes(2);
			}
		}
	}

	public final WorldInfo getInfo() {
		return info;
	}

	public final Tile getTile(Position position) {
		checkArgument(info.getBounds().contains(position));

		Tile modified = modifiedTiles.get(Integer.valueOf(position.getX() * info.getHeight() + position.getY()));
		if (modified != null)
			return modified;

		try {
			buffer.position(tileBufferPos[position.getX()]);
			skipTiles(input, position.getY());
			return new Tile(input);
		} catch (IOException e) {
			throw new RuntimeException("Error reading tile");
		}
	}

	public final void setTile(Position position, Tile tile) {
		checkArgument(info.getBounds().contains(position));

		modifiedTiles.put(Integer.valueOf(info.getHeight() * position.getX() + position.getY()), checkNotNull(tile));
	}

	public final TileGroup getTileGroup(Rectangle rect) {
		checkNotNull(rect);

		try {
			Tile[] tileData = new Tile[rect.getWidth() * rect.getHeight()];
			int i = 0;

			for (int x = rect.getLeft(); x < rect.getRight(); x++) {
				buffer.position(tileBufferPos[x]);
				skipTiles(input, rect.getTop());

				for (int y = rect.getTop(); y < rect.getBottom(); y++) {
					tileData[i++] = new Tile(input);
				}
			}

			return new TileGroup(rect, tileData);
		} catch (IOException e) {
			throw new RuntimeException("Error reading tiles");
		}
	}

	public final Map<Position, Chest> getChests() {
		return chests;
	}

	public final Map<Position, String> getSigns() {
		return signs;
	}

	public final Map<Position, NPC> getNpcs() {
		return npcs;
	}
}
