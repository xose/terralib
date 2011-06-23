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

public enum ItemType {
	Nothing(0, 0, ""),
	IronPickaxe(1, 1, "Iron Pickaxe"),
	DirtBlock(2, 250, "Dirt Block"),
	StoneBlock(3, 250, "Stone Block"),
	IronBroadsword(4, 1, "Iron Broadsword"),
	Mushroom(5, 99, "Mushroom"),
	IronShortsword(6, 1, "Iron Shortsword"),
	IronHammer(7, 1, "Iron Hammer"),
	Torch(8, 99, "Torch"),
	Wood(9, 250, "Wood"),
	IronAxe(10, 1, "Iron Axe"),
	IronOre(11, 99, "Iron Ore"),
	CopperOre(12, 99, "Copper Ore"),
	GoldOre(13, 99, "Gold Ore"),
	SilverOre(14, 99, "Silver Ore"),
	CopperWatch(15, 1, "Copper Watch"),
	SilverWatch(16, 1, "Silver Watch"),
	GoldWatch(17, 1, "Gold Watch"),
	DepthMeter(18, 1, "Depth Meter"),
	GoldBar(19, 99, "Gold Bar"),
	CopperBar(20, 99, "Copper Bar"),
	SilverBar(21, 99, "Silver Bar"),
	IronBar(22, 99, "Iron Bar"),
	Gel(23, 99, "Gel"),
	WoodenSword(24, 1, "Wooden Sword"),
	WoodenDoor(25, 99, "Wooden Door"),
	StoneWall(26, 250, "Stone Wall"),
	Acorn(27, 99, "Acorn"),
	LesserHealingPotion(28, 30, "Lesser Healing Potion"),
	LifeCrystal(29, 99, "Life Crystal"),
	DirtWall(30, 250, "Dirt Wall"),
	Bottle(31, 99, "Bottle"),
	WoodenTable(32, 99, "Wooden Table"),
	Furnace(33, 99, "Furnace"),
	WoodenChair(34, 99, "Wooden Chair"),
	IronAnvil(35, 99, "Iron Anvil"),
	WorkBench(36, 99, "Work Bench"),
	Goggles(37, 1, "Goggles"),
	Lens(38, 99, "Lens"),
	WoodenBow(39, 1, "Wooden Bow"),
	WoodenArrow(40, 250, "Wooden Arrow"),
	FlamingArrow(41, 250, "Flaming Arrow"),
	Shuriken(42, 250, "Shuriken"),
	SuspiciousLookingEye(43, 1, "Suspicious Looking Eye"),
	DemonBow(44, 1, "Demon Bow"),
	WarAxeoftheNight(45, 1, "War Axe of the Night"),
	LightsBane(46, 1, "Light's Bane"),
	UnholyArrow(47, 250, "Unholy Arrow"),
	Chest(48, 99, "Chest"),
	BandofRegeneration(49, 1, "Band of Regeneration"),
	MagicMirror(50, 1, "Magic Mirror"),
	JestersArrow(51, 250, "Jester's Arrow"),
	AngelStatue(52, 1, "Angel Statue"),
	CloudinaBottle(53, 1, "Cloud in a Bottle"),
	HermesBoots(54, 1, "Hermes Boots"),
	EnchantedBoomerang(55, 1, "Enchanted Boomerang"),
	DemoniteOre(56, 99, "Demonite Ore"),
	DemoniteBar(57, 99, "Demonite Bar"),
	Heart(58, 1, "Heart"),
	CorruptSeeds(59, 99, "Corrupt Seeds"),
	VileMushroom(60, 99, "Vile Mushroom"),
	EbonstoneBlock(61, 250, "Ebonstone Block"),
	GrassSeeds(62, 99, "Grass Seeds"),
	Sunflower(63, 99, "Sunflower"),
	Vilethorn(64, 1, "Vilethorn"),
	Starfury(65, 1, "Starfury"),
	PurificationPowder(66, 99, "Purification Powder"),
	VilePowder(67, 99, "Vile Powder"),
	RottenChunk(68, 99, "Rotten Chunk"),
	WormTooth(69, 99, "Worm Tooth"),
	WormFood(70, 1, "Worm Food"),
	CopperCoin(71, 100, "Copper Coin"),
	SilverCoin(72, 100, "Silver Coin"),
	GoldCoin(73, 100, "Gold Coin"),
	PlatinumCoin(74, 100, "Platinum Coin"),
	FallenStar(75, 100, "Fallen Star"),
	CopperGreaves(76, 1, "Copper Greaves"),
	IronGreaves(77, 1, "Iron Greaves"),
	SilverGreaves(78, 1, "Silver Greaves"),
	GoldGreaves(79, 1, "Gold Greaves"),
	CopperChainmail(80, 1, "Copper Chainmail"),
	IronChainmail(81, 1, "Iron Chainmail"),
	SilverChainmail(82, 1, "Silver Chainmail"),
	GoldChainmail(83, 1, "Gold Chainmail"),
	GrapplingHook(84, 1, "Grappling Hook"),
	IronChain(85, 99, "Iron Chain"),
	ShadowScale(86, 99, "Shadow Scale"),
	PiggyBank(87, 99, "Piggy Bank"),
	MiningHelmet(88, 1, "Mining Helmet"),
	CopperHelmet(89, 1, "Copper Helmet"),
	IronHelmet(90, 1, "Iron Helmet"),
	SilverHelmet(91, 1, "Silver Helmet"),
	GoldHelmet(92, 1, "Gold Helmet"),
	WoodWall(93, 250, "Wood Wall"),
	WoodPlatform(94, 99, "Wood Platform"),
	FlintlockPistol(95, 1, "Flintlock Pistol"),
	Musket(96, 1, "Musket"),
	MusketBall(97, 250, "Musket Ball"),
	Minishark(98, 1, "Minishark"),
	IronBow(99, 1, "Iron Bow"),
	ShadowGreaves(100, 1, "Shadow Greaves"),
	ShadowScalemail(101, 1, "Shadow Scalemail"),
	ShadowHelmet(102, 1, "Shadow Helmet"),
	NightmarePickaxe(103, 1, "Nightmare Pickaxe"),
	TheBreaker(104, 1, "The Breaker"),
	Candle(105, 99, "Candle"),
	CopperChandelier(106, 99, "Copper Chandelier"),
	SilverChandelier(107, 99, "Silver Chandelier"),
	GoldChandelier(108, 99, "Gold Chandelier"),
	ManaCrystal(109, 99, "Mana Crystal"),
	LesserManaPotion(110, 30, "Lesser Mana Potion"),
	BandofStarpower(111, 1, "Band of Starpower"),
	FlowerofFire(112, 1, "Flower of Fire"),
	MagicMissile(113, 1, "Magic Missile"),
	DirtRod(114, 1, "Dirt Rod"),
	OrbofLight(115, 1, "Orb of Light"),
	Meteorite(116, 250, "Meteorite"),
	MeteoriteBar(117, 99, "Meteorite Bar"),
	Hook(118, 99, "Hook"),
	Flamarang(119, 1, "Flamarang"),
	MoltenFury(120, 1, "Molten Fury"),
	FieryGreatsword(121, 1, "Fiery Greatsword"),
	MoltenPickaxe(122, 1, "Molten Pickaxe"),
	MeteorHelmet(123, 1, "Meteor Helmet"),
	MeteorSuit(124, 1, "Meteor Suit"),
	MeteorLeggings(125, 1, "Meteor Leggings"),
	AngelStatue2(126, 1, "Angel Statue"),
	SpaceGun(127, 1, "Space Gun"),
	RocketBoots(128, 1, "Rocket Boots"),
	GrayBrick(129, 250, "Gray Brick"),
	GrayBrickWall(130, 250, "Gray Brick Wall"),
	RedBrick(131, 250, "Red Brick"),
	RedBrickWall(132, 250, "Red Brick Wall"),
	ClayBlock(133, 250, "Clay Block"),
	BlueBrick(134, 250, "Blue Brick"),
	BlueBrickWall(135, 250, "Blue Brick Wall"),
	ChainLantern(136, 250, "Chain Lantern"),
	GreenBrick(137, 250, "Green Brick"),
	GreenBrickWall(138, 250, "Green Brick Wall"),
	PinkBrick(139, 250, "Pink Brick"),
	PinkBrickWall(140, 250, "Pink Brick Wall"),
	GoldBrick(141, 250, "Gold Brick"),
	GoldBrickWall(142, 250, "Gold Brick Wall"),
	SilverBrick(143, 250, "Silver Brick"),
	SilverBrickWall(144, 250, "Silver Brick Wall"),
	CopperBrick(145, 250, "Copper Brick"),
	CopperBrickWall(146, 250, "Copper Brick Wall"),
	Spike(147, 250, "Spike"),
	WaterCandle(148, 99, "Water Candle"),
	Book(149, 99, "Book"),
	Cobweb(150, 250, "Cobweb"),
	NecroHelmet(151, 1, "Necro Helmet"),
	NecroBreastplate(152, 1, "Necro Breastplate"),
	NecroGreaves(153, 1, "Necro Greaves"),
	Bone(154, 99, "Bone"),
	Muramasa(155, 1, "Muramasa"),
	CobaltShield(156, 1, "Cobalt Shield"),
	AquaScepter(157, 1, "Aqua Scepter"),
	LuckyHorseshoe(158, 1, "Lucky Horseshoe"),
	ShinyRedBalloon(159, 1, "Shiny Red Balloon"),
	Harpoon(160, 1, "Harpoon"),
	SpikyBall(161, 250, "Spiky Ball"),
	BallOHurt(162, 1, "Ball 'O Hurt"),
	BlueMoon(163, 1, "Blue Moon"),
	Handgun(164, 1, "Handgun"),
	WaterBolt(165, 1, "Water Bolt"),
	Bomb(166, 20, "Bomb"),
	Dynamite(167, 3, "Dynamite"),
	Grenade(168, 20, "Grenade"),
	SandBlock(169, 250, "Sand Block"),
	Glass(170, 250, "Glass"),
	Sign(171, 250, "Sign"),
	AshBlock(172, 250, "Ash Block"),
	Obsidian(173, 250, "Obsidian"),
	Hellstone(174, 250, "Hellstone"),
	HellstoneBar(175, 99, "Hellstone Bar"),
	MudBlock(176, 250, "Mud Block"),
	Sapphire(177, 99, "Sapphire"),
	Ruby(178, 99, "Ruby"),
	Emerald(179, 99, "Emerald"),
	Topaz(180, 99, "Topaz"),
	Amethyst(181, 99, "Amethyst"),
	Diamond(182, 99, "Diamond"),
	GlowingMushroom(183, 99, "Glowing Mushroom"),
	Star(184, 1, "Star"),
	IvyWhip(185, 1, "Ivy Whip"),
	BreathingReed(186, 1, "Breathing Reed"),
	Flipper(187, 1, "Flipper"),
	HealingPotion(188, 30, "Healing Potion"),
	ManaPotion(189, 30, "Mana Potion"),
	BladeofGrass(190, 1, "Blade of Grass"),
	ThornChakrum(191, 1, "Thorn Chakrum"),
	ObsidianBrick(192, 250, "Obsidian Brick"),
	ObsidianSkull(193, 1, "Obsidian Skull"),
	MushroomGrassSeeds(194, 99, "Mushroom Grass Seeds"),
	JungleGrassSeeds(195, 99, "Jungle Grass Seeds"),
	WoodenHammer(196, 1, "Wooden Hammer"),
	StarCannon(197, 1, "Star Cannon"),
	BluePhaseblade(198, 1, "Blue Phaseblade"),
	RedPhaseblade(199, 1, "Red Phaseblade"),
	GreenPhaseblade(200, 1, "Green Phaseblade"),
	PurplePhaseblade(201, 1, "Purple Phaseblade"),
	WhitePhaseblade(202, 1, "White Phaseblade"),
	YellowPhaseblade(203, 1, "Yellow Phaseblade"),
	MeteorHamaxe(204, 1, "Meteor Hamaxe"),
	EmptyBucket(205, 1, "Empty Bucket"),
	WaterBucket(206, 1, "Water Bucket"),
	LavaBucket(207, 1, "Lava Bucket"),
	JungleRose(208, 99, "Jungle Rose"),
	Stinger(209, 99, "Stinger"),
	Vine(210, 99, "Vine"),
	FeralClaws(211, 1, "Feral Claws"),
	AnkletoftheWind(212, 1, "Anklet of the Wind"),
	StaffofRegrowth(213, 1, "Staff of Regrowth"),
	HellstoneBrick(214, 250, "Hellstone Brick"),
	WhoopieCushion(215, 1, "Whoopie Cushion"),
	Shackle(216, 1, "Shackle"),
	MoltenHamaxe(217, 1, "Molten Hamaxe"),
	Flamelash(218, 1, "Flamelash"),
	PhoenixBlaster(219, 1, "Phoenix Blaster"),
	Sunfury(220, 1, "Sunfury"),
	Hellforge(221, 99, "Hellforge"),
	ClayPot(222, 99, "Clay Pot"),
	NaturesGift(223, 1, "Nature's Gift"),
	Bed(224, 99, "Bed"),
	Silk(225, 99, "Silk"),
	LesserRestorationPotion(226, 20, "Lesser Restoration Potion"),
	RestorationPotion(227, 20, "Restoration Potion"),
	JungleHat(228, 1, "Jungle Hat"),
	JungleShirt(229, 1, "Jungle Shirt"),
	JunglePants(230, 1, "Jungle Pants"),
	MoltenHelmet(231, 1, "Molten Helmet"),
	MoltenBreastplate(232, 1, "Molten Breastplate"),
	MoltenGreaves(233, 1, "Molten Greaves"),
	MeteorShot(234, 250, "Meteor Shot"),
	StickyBomb(235, 20, "Sticky Bomb"),
	BlackLens(236, 99, "Black Lens"),
	Sunglasses(237, 1, "Sunglasses"),
	WizardHat(238, 1, "Wizard Hat"),
	TopHat(239, 1, "Top Hat"),
	TuxedoShirt(240, 1, "Tuxedo Shirt"),
	TuxedoPants(241, 1, "Tuxedo Pants"),
	SummerHat(242, 1, "Summer Hat"),
	BunnyHood(243, 1, "Bunny Hood"),
	PlumbersHat(244, 1, "Plumber's Hat"),
	PlumbersShirt(245, 1, "Plumber's Shirt"),
	PlumbersPants(246, 1, "Plumber's Pants"),
	HerosHat(247, 1, "Hero's Hat"),
	HerosShirt(248, 1, "Hero's Shirt"),
	HerosPants(249, 1, "Hero's Pants"),
	FishBowl(250, 1, "Fish Bowl"),
	ArchaeologistsHat(251, 1, "Archaeologist's Hat"),
	ArchaeologistsJacket(252, 1, "Archaeologist's Jacket"),
	ArchaeologistsPants(253, 1, "Archaeologist's Pants"),
	BlackDye(254, 99, "Black Dye"),
	GreenDye(255, 99, "Green Dye"),
	NinjaHood(256, 1, "Ninja Hood"),
	NinjaShirt(257, 1, "Ninja Shirt"),
	NinjaPants(258, 1, "Ninja Pants"),
	Leather(259, 99, "Leather"),
	RedHat(260, 1, "Red Hat"),
	Goldfish(261, 99, "Goldfish"),
	Robe(262, 1, "Robe"),
	RobotHat(263, 1, "Robot Hat"),
	GoldCrown(264, 1, "Gold Crown");

	private static final ItemType[] itemTable;
	static {
		itemTable = new ItemType[values().length];

		for (ItemType type : values()) {
			itemTable[type.getID()] = type;
		}
	}

	public final static ItemType fromID(int id) {
		checkElementIndex(id, itemTable.length);
		return itemTable[id];
	}

	public static final ItemType fromString(String string) {
		for (ItemType type : values()) {
			if (type.toString().equals(string))
				return type;
		}

		throw new IllegalArgumentException("Unknown item: " + string);
	}

	private final int id;
	private final int maxStack;
	private final String name;

	private ItemType(int id, int maxStack, String name) {
		this.id = id;
		this.maxStack = maxStack;
		this.name = name;
	}

	public final int getID() {
		return id;
	}

	public final int getMaxStack() {
		return maxStack;
	}

	@Override
	public final String toString() {
		return name;
	}
}
