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

package terralib.example;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import terralib.Chest;
import terralib.NPC;
import terralib.Position;
import terralib.Stack;
import terralib.World;
import terralib.WorldInfo;

public final class MapInfo {

	public static final void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out.println("Specify a path to a Terraria world file.");
			System.exit(1);
		}

		long init = System.currentTimeMillis();
		World w = new World(new File(args[0]));
		WorldInfo info = w.getInfo();

		// Print map info
		System.out.println("Loaded map: " + info.getName() + " (ID: " + info.getID() + ") in " + (System.currentTimeMillis() - init)/1000.f + " seconds");
		System.out.println("Bounds: " + info.getBounds().toString());
		System.out.println("Spawn: " + info.getSpawn());
		System.out.println("Ground: " + info.getGroundLevel());
		System.out.println("Rock: " + info.getRockLevel());

		// Print Chests
		for (Entry<Position, Chest> entry : w.getChests().entrySet()) {
			Chest c = entry.getValue();
			System.out.println("-- Chest: " + entry.getKey().toString() + " --");
			for (int i = 0; i < c.getSize(); i++) {
				Stack s = c.getStack(i);
				if (s.getSize() > 0) {
					System.out.println(s.getItemType().toString() + " (" + s.getSize() + ")");
				}
			}
		}

		// Print NPCs
		System.out.println("-- NPCs --");
		for (Entry<Position, NPC> entry : w.getNpcs().entrySet()) {
			System.out.println(entry.getValue().getType().toString() + ": " + entry.getKey().toString());
		}
	}
}
