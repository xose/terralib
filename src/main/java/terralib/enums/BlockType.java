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

public enum BlockType {
	None(-1),

	Dirt(0),
	Stone(1),
	Grass(2),
	Plant(3, true),
	Torch(4),
	Tree(5, true),
	Iron(6),
	Copper(7),
	Gold(8),
	Silver(9),

	ClosedDoor(10, true),
	OpenDoor(11, true),
	HeartStone(12, true),
	Bottle(13, true),
	Table(14, true),
	Chair(15, true),
	Anvil(16, true),
	Furnace(17, true),
	CraftingTable(18, true),
	WoodenPlatform(19),

	Sapling(20, true),
	Chest(21, true),
	CorruptedStone(22),
	CorruptedGrass(23),
	CorruptedPlant(24, true),
	CorruptedStone2(25),
	DemonAltar(26, true),
	Sunflower(27, true),
	Pot(28, true),
	PiggyBank(29, true),

	Wood(30),
	ShadowOrb(31, true),
	CorruptedVines(32),
	Candle(33, true),
	CopperChandelier(34, true),
	SilverChandelier(35, true),
	GoldChandelier(36, true),
	Meteorite(37),
	GrayBrick(38),
	ClayBrick(39),

	Clay(40),
	BlueBrick(41),
	LightGlobe(42, true),
	GreenBrick(43),
	PinkBrick(44),
	GoldBrick(45),
	SilverBrick(46),
	CopperBrick(47),
	Spikes(48),
	BlueCandle(49),

	Books(50, true),
	Cobweb(51),
	Vines(52),
	Sand(53),
	Glass(54),
	Sign(55, true),
	Obsidian(56),
	Ash(57),
	Hellstone(58),
	Mud(59),

	JungleGrass(60),
	JunglePlant(61, true),
	JungleVines(62),
	Sapphire(63),
	Ruby(64),
	Emerald(65),
	Topaz(66),
	Amethyst(67),
	Diamond(68),
	JungleThorn(69),

	MushroomGrass(70),
	Mushroom(71, true),
	MushroomTree(72, true),
	Plant2(73, true),
	Plant3(74, true),
	ObsidianBrick(75),
	HellstoneBrick(76),
	Hellforge(77, true),
	ClayPot(78, true),
	Bed(79, true);

	private static final BlockType[] blockTable;
	static {
		blockTable = new BlockType[values().length];

		for (BlockType type : values()) {
			blockTable[type.getID() + 1] = type;
		}
	}

	public final static BlockType fromID(int id) {
		checkElementIndex(id + 1, blockTable.length);
		return blockTable[id + 1];
	}

	private final int id;
	private final boolean extra;

	private BlockType(int id) {
		this(id, false);
	}

	private BlockType(int id, boolean extra) {
		this.id = id;
		this.extra = extra;
	}

	public final int getID() {
		return id;
	}

	public final boolean hasExtra() {
		return extra;
	}
}
