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

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import terralib.enums.BlockType;
import terralib.enums.LiquidType;
import terralib.enums.WallType;

import com.google.common.base.Objects;

public final class Tile extends AbstractDataObject {

	private BlockType blockType = BlockType.None;
	private int u = -1;
	private int v = -1;

	private WallType wallType = WallType.None;

	private LiquidType liquidType = LiquidType.None;
	private int liquidLevel;

	private boolean sunlight;

	public Tile() {
	}

	protected Tile(DataInput input) throws IOException {
		parse(input);
	}

	@Override
	protected final void parse(DataInput input) throws IOException {
		if (input.readBoolean()) {
			blockType = BlockType.fromID(input.readUnsignedByte());

			if (blockType.hasExtra()) {
				u = input.readUnsignedShort();
				v = input.readUnsignedShort();
			} else {
				u = v = -1;
			}
		} else {
			blockType = BlockType.None;
			u = v = -1;
		}

		sunlight = input.readBoolean();

		if (input.readBoolean()) {
			wallType = WallType.fromID(input.readUnsignedByte());
		} else {
			wallType = WallType.None;
		}

		if (input.readBoolean()) {
			liquidLevel = input.readUnsignedByte();
			liquidType = input.readBoolean() ? LiquidType.Lava : LiquidType.Water;
		} else {
			liquidLevel = 0;
			liquidType = LiquidType.None;
		}
	}

	@Override
	protected final void write(DataOutput output) throws IOException {
		output.writeBoolean(blockType != BlockType.None);
		if (blockType != BlockType.None) {
			output.writeByte(blockType.getID());

			if (blockType.hasExtra()) {
				output.writeShort(u);
				output.writeShort(v);
			}
		}

		output.writeBoolean(sunlight);

		output.writeBoolean(wallType != WallType.None);
		if (wallType != WallType.None) {
			output.writeByte(wallType.getID());
		}

		output.writeBoolean(liquidType != LiquidType.None);
		if (liquidType != LiquidType.None) {
			output.writeByte(liquidLevel);
			output.writeBoolean(liquidType == LiquidType.Lava);
		}
	}

	public final void updateTileUV(Tile N, Tile NE, Tile E, Tile SE, Tile S, Tile SW, Tile W, Tile NW) {
		if (blockType.hasExtra())
			return;

		// TODO
	}

	public final BlockType getBlockType() {
		return blockType;
	}

	public final void setBlockType(BlockType blockType) {
		this.blockType = checkNotNull(blockType);
	}

	public final int getU() {
		return u;
	}

	public final void setU(int u) {
		this.u = u;
	}

	public final int getV() {
		return v;
	}

	public final void setV(int v) {
		this.v = v;
	}

	public final WallType getWallType() {
		return wallType;
	}

	public final void setWallType(WallType wallType) {
		this.wallType = checkNotNull(wallType);
	}

	public final LiquidType getLiquidType() {
		return liquidType;
	}

	public final void setLiquidType(LiquidType liquidType) {
		this.liquidType = checkNotNull(liquidType);

		if (liquidType == LiquidType.None) {
			liquidLevel = 0;
		}
	}

	public final int getLiquidLevel() {
		return liquidLevel;
	}

	public final void setLiquidLevel(int liquidLevel) {
		checkArgument(liquidLevel >= 0 && liquidLevel <= 256);
		this.liquidLevel = liquidLevel;
	}

	public final boolean isSunlight() {
		return sunlight;
	}

	public final void setSunlight(boolean sunlight) {
		this.sunlight = sunlight;
	}

	@Override
	public final String toString() {
		return Objects.toStringHelper(this).add("Block Type", blockType).add("Wall Type", wallType).add("Liquid Type", liquidType)
				.add("Liquid Level", Integer.valueOf(liquidLevel)).add("Sunlight", Boolean.valueOf(sunlight)).add("U", Integer.valueOf(u))
				.add("V", Integer.valueOf(v)).toString();
	}
}
