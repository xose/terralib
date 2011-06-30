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

import terralib.util.CSharpData;

import com.google.common.base.Objects;

public final class WorldInfo extends AbstractDataObject {

	private String name = "";
	private int id;

	private Rectangle bounds = new Rectangle();
	private int height;
	private int width;
	private Position spawn = new Position();

	private int groundLevel;
	private int rockLevel;
	private double time;

	private boolean night;
	private int moon;
	private boolean bloodMoon;

	private Position dungeon = new Position();

	private boolean boss1;
	private boolean boss2;
	private boolean boss3;

	private boolean brokenOrb;
	private boolean meteor;
	private int shadowOrbs;

	private int goblinTime;
	private int goblinSize;
	private int goblinType;
	private double goblinPos;

	protected WorldInfo() {
	}

	protected WorldInfo(DataInput input) throws IOException {
		parse(input);
	}

	@Override
	protected final void parse(DataInput input) throws IOException {
		name = CSharpData.readString(input);
		id = input.readInt();

		bounds.parse(input);
		height = input.readInt();
		width = input.readInt();
		if (height*16 != bounds.getHeight() || width*16 != bounds.getWidth())
			throw new MapParsingException(this, "Height/Width mismatch");

		spawn.parse(input);
		if (!bounds.contains(spawn))
			throw new MapParsingException(this, "Spawn out of bounds");

		groundLevel = (int) input.readDouble();
		rockLevel = (int) input.readDouble();
		time = input.readDouble();

		night = input.readBoolean();
		moon = input.readInt();
		bloodMoon = input.readBoolean();

		dungeon.parse(input);
		if (!bounds.contains(dungeon))
			throw new MapParsingException(this, "Dungeon out of bounds");

		boss1 = input.readBoolean();
		boss2 = input.readBoolean();
		boss3 = input.readBoolean();

		brokenOrb = input.readBoolean();
		meteor = input.readBoolean();
		shadowOrbs = input.readUnsignedByte();

		goblinTime = input.readInt();
		goblinSize = input.readInt();
		goblinType = input.readInt();
		goblinPos = input.readDouble();
	}

	@Override
	protected final void write(DataOutput output) throws IOException {
		CSharpData.writeString(output, name);
		output.writeInt(id);

		bounds.write(output);
		output.writeInt(height);
		output.writeInt(width);
		spawn.write(output);

		output.writeDouble(groundLevel);
		output.writeDouble(rockLevel);
		output.writeDouble(time);

		output.writeBoolean(night);
		output.writeInt(moon);
		output.writeBoolean(bloodMoon);

		dungeon.write(output);

		output.writeBoolean(boss1);
		output.writeBoolean(boss2);
		output.writeBoolean(boss3);

		output.writeBoolean(brokenOrb);
		output.writeBoolean(meteor);
		output.writeByte(shadowOrbs);

		output.writeInt(goblinTime);
		output.writeInt(goblinSize);
		output.writeInt(goblinType);
		output.writeDouble(goblinPos);
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = checkNotNull(name);
	}

	public final int getID() {
		return id;
	}

	public final void setID(int id) {
		this.id = id;
	}

	public final Rectangle getBounds() {
		return bounds;
	}

	public final void setBounds(Rectangle bounds) {
		this.bounds = checkNotNull(bounds);
	}

	public final int getHeight() {
		return height;
	}

	public final void setHeight(int height) {
		checkArgument(height > 0);
		this.height = height;
	}

	public final int getWidth() {
		return width;
	}

	public final void setWidth(int width) {
		checkArgument(width > 0);
		this.width = width;
	}

	public final Position getSpawn() {
		return spawn;
	}

	public final void setSpawn(Position spawn) {
		this.spawn = checkNotNull(spawn);
	}

	public final int getGroundLevel() {
		return groundLevel;
	}

	public final void setGroundLevel(int groundLevel) {
		checkArgument(groundLevel > 0);
		this.groundLevel = groundLevel;
	}

	public final int getRockLevel() {
		return rockLevel;
	}

	public final void setRockLevel(int rockLevel) {
		checkArgument(rockLevel > 0);
		this.rockLevel = rockLevel;
	}

	public final double getTime() {
		return time;
	}

	public final void setTime(double time) {
		this.time = time;
	}

	public final boolean isNight() {
		return night;
	}

	public final void setNight(boolean night) {
		this.night = night;
	}

	public final int getMoon() {
		return moon;
	}

	public final void setMoon(int moon) {
		this.moon = moon;
	}

	public final boolean isBloodMoon() {
		return bloodMoon;
	}

	public final void setBloodMoon(boolean bloodMoon) {
		this.bloodMoon = bloodMoon;
	}

	public final Position getDungeon() {
		return dungeon;
	}

	public final void setDungeon(Position dungeon) {
		this.dungeon = checkNotNull(dungeon);
	}

	public final boolean isBoss1() {
		return boss1;
	}

	public final void setBoss1(boolean boss1) {
		this.boss1 = boss1;
	}

	public final boolean isBoss2() {
		return boss2;
	}

	public final void setBoss2(boolean boss2) {
		this.boss2 = boss2;
	}

	public final boolean isBoss3() {
		return boss3;
	}

	public final void setBoss3(boolean boss3) {
		this.boss3 = boss3;
	}

	public final boolean isBrokenOrb() {
		return brokenOrb;
	}

	public final void setBrokenOrb(boolean brokenOrb) {
		this.brokenOrb = brokenOrb;
	}

	public final boolean isMeteor() {
		return meteor;
	}

	public final void setMeteor(boolean meteor) {
		this.meteor = meteor;
	}

	public final int getShadowOrbs() {
		return shadowOrbs;
	}

	public final void setShadowOrbs(int shadowOrbs) {
		checkArgument(shadowOrbs > 0);
		this.shadowOrbs = shadowOrbs;
	}

	public final int getGoblinTime() {
		return goblinTime;
	}

	public final void setGoblinTime(int goblinTime) {
		this.goblinTime = goblinTime;
	}

	public final int getGoblinSize() {
		return goblinSize;
	}

	public final void setGoblinSize(int goblinSize) {
		this.goblinSize = goblinSize;
	}

	public final int getGoblinType() {
		return goblinType;
	}

	public final void setGoblinType(int goblinType) {
		this.goblinType = goblinType;
	}

	public final double getGoblinPos() {
		return goblinPos;
	}

	public final void setGoblinPos(double goblinPos) {
		this.goblinPos = goblinPos;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("ID", Integer.valueOf(id)).add("Name", name).add("Height", Integer.valueOf(height))
				.add("Width", Integer.valueOf(width)).add("Spawn", spawn).toString();
	}
}
