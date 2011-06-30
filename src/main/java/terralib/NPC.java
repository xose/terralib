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

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import terralib.enums.NpcType;
import terralib.util.CSharpData;

import com.google.common.base.Objects;

public final class NPC extends AbstractDataObject {

	private NpcType type = NpcType.Guide;
	private PositionFloat position = new PositionFloat();
	private boolean home;

	public NPC() {
	}

	public NPC(NpcType type, PositionFloat position, boolean home) {
		this.type = type;
		this.position = position;
		this.home = home;
	}

	protected NPC(DataInput input) throws IOException {
		parse(input);
	}

	@Override
	protected final void parse(DataInput input) throws IOException {
		type = NpcType.fromString(CSharpData.readString(input));
		position.parse(input);
		home = !input.readBoolean();
	}

	@Override
	protected final void write(DataOutput output) throws IOException {
		CSharpData.writeString(output, type.toString());
		position.write(output);
		output.writeBoolean(!home);
	}

	public final NpcType getType() {
		return type;
	}

	public final void setType(NpcType type) {
		this.type = checkNotNull(type);
	}

	public final PositionFloat getPosition() {
		return position;
	}

	public final void setPosition(PositionFloat position) {
		this.position = checkNotNull(position);
	}

	public final boolean hasHome() {
		return home;
	}

	public final void setHome(boolean home) {
		this.home = home;
	}

	@Override
	public final String toString() {
		return Objects.toStringHelper(this).add("Type", type).add("Position", position).add("Home", Boolean.valueOf(home)).toString();
	}
}
