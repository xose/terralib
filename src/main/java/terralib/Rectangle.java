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

public final class Rectangle {

	private final int left;
	private final int right;
	private final int top;
	private final int bottom;

	public Rectangle(int left, int right, int top, int bottom) {
		checkArgument(left < right);
		checkArgument(top < bottom);
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	protected Rectangle(DataInput input) throws IOException {
		left = input.readInt();
		right = input.readInt();
		if (left > right)
			throw new MapParsingException(this, "left > right");

		top = input.readInt();
		bottom = input.readInt();
		if (top > bottom)
			throw new MapParsingException(this, "top > bottom");
	}

	protected final void write(DataOutput output) throws IOException {
		output.writeInt(left);
		output.writeInt(right);
		output.writeInt(top);
		output.writeInt(bottom);
	}

	public final boolean contains(Position position) {
		return left <= position.getX() && position.getX() <= right && bottom <= position.getY() && position.getY() <= top;
	}

	public final boolean contains(Rectangle rect) {
		return left <= rect.left && rect.right <= right && bottom <= rect.bottom && rect.top <= top;
	}

	public final int getLeft() {
		return left;
	}

	public final int getRight() {
		return right;
	}

	public final int getTop() {
		return top;
	}

	public final int getBottom() {
		return bottom;
	}

	public final int getWidth() {
		return right - left;
	}

	public final int getHeight() {
		return bottom - top;
	}

	@Override
	public final String toString() {
		return Objects.toStringHelper(this).add("Left", Integer.valueOf(left)).add("Right", Integer.valueOf(right)).add("Top", Integer.valueOf(top))
				.add("Bottom", Integer.valueOf(bottom)).toString();
	}
}
