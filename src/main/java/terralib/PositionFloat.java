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

public final class PositionFloat extends AbstractDataObject {

	private float x;
	private float y;

	public PositionFloat() {
	}

	public PositionFloat(float x, float y) {
		checkArgument(x >= 0 && y >= 0);
		this.x = x;
		this.y = y;
	}

	protected PositionFloat(DataInput input) throws IOException {
		parse(input);
	}

	@Override
	protected final void parse(DataInput input) throws IOException {
		x = input.readFloat();
		y = input.readFloat();

		if (x < 0 || y < 0)
			throw new MapParsingException(this, "< 0");
	}

	@Override
	protected final void write(DataOutput output) throws IOException {
		output.writeFloat(x);
		output.writeFloat(y);
	}

	public final float getX() {
		return x;
	}

	public final float getY() {
		return y;
	}

	@Override
	public final boolean equals(Object obj) {
		if (obj == null || !(obj instanceof PositionFloat))
			return false;

		PositionFloat other = (PositionFloat) obj;
		if (x != other.x || y != other.y)
			return false;

		return true;
	}

	@Override
	public final int hashCode() {
		return Objects.hashCode(Float.valueOf(x), Float.valueOf(y));
	}

	@Override
	public final String toString() {
		return Objects.toStringHelper(this).add("X", Float.valueOf(x)).add("Y", Float.valueOf(y)).toString();
	}
}
