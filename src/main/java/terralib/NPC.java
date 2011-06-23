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

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import terralib.util.CSharpData;

import com.google.common.base.Objects;

public final class NPC {

	private String name;
	private PositionFloat position;
	private boolean homeless;

	public NPC(String name, PositionFloat position, boolean homeless) {
		this.name = name;
		this.position = position;
		this.homeless = homeless;
	}

	protected NPC(DataInput input) throws IOException {
		name = CSharpData.readString(input);
		position = new PositionFloat(input);
		homeless = input.readBoolean();
	}

	protected final void write(DataOutput output) throws IOException {
		CSharpData.writeString(output, name);
		position.write(output);
		output.writeBoolean(homeless);
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		checkArgument(name != null);
		this.name = name;
	}

	public final PositionFloat getPosition() {
		return position;
	}

	public final void setPosition(PositionFloat position) {
		checkArgument(position != null);
		this.position = position;
	}

	public final boolean isHomeless() {
		return homeless;
	}

	public final void setHomeless(boolean homeless) {
		this.homeless = homeless;
	}

	@Override
	public final String toString() {
		return Objects.toStringHelper(this).add("Name", name).add("Position", position).add("Homeless", Boolean.valueOf(homeless)).toString();
	}
}
