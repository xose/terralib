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

package terralib.enums;

import static com.google.common.base.Preconditions.checkElementIndex;

public enum WallType {
	None(0),
	Stone(1),
	Dirt(2),
	Stone2(3),
	Wood(4),
	Brick(5),
	RedBrick(6),
	BlueBrick(7),
	GreenBrick(8),
	PinkBrick(9),
	GoldBrick(10),
	SilverBrick(11),
	CopperBrick(12),
	HellstoneBrick(13);

	private static final WallType[] wallTable;
	static {
		wallTable = new WallType[values().length];

		for (WallType type : values()) {
			wallTable[type.getID()] = type;
		}
	}

	public static final WallType fromID(int id) {
		checkElementIndex(id, wallTable.length);
		return wallTable[id];
	}

	private final int id;

	private WallType(int id) {
		this.id = id;
	}

	public final int getID() {
		return id;
	}
}
