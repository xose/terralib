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

import com.google.common.base.Objects;

public final class Position extends AbstractDataObject {

	private int x;
	private int y;

	public Position() {
	}

	public Position(int x, int y) {
		checkArgument(x >= 0 && y >= 0);
		this.x = x;
		this.y = y;
	}

	protected Position(DataInput input) throws IOException {
		parse(input);
	}

	@Override
	protected final void parse(DataInput input) throws IOException {
		x = input.readInt();
		y = input.readInt();

		if (x < 0 || y < 0)
			throw new MapParsingException(this, "< 0");
	}

	@Override
	protected final void write(DataOutput output) throws IOException {
		output.writeInt(x);
		output.writeInt(y);
	}

	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Position))
			return false;

		Position other = (Position) obj;
		if (x != other.x || y != other.y)
			return false;

		return true;
	}

	@Override
	public final int hashCode() {
		return Objects.hashCode(Integer.valueOf(x), Integer.valueOf(y));
	}

	@Override
	public final String toString() {
		return Objects.toStringHelper(this).add("X", Integer.valueOf(x)).add("Y", Integer.valueOf(y)).toString();
	}
}
