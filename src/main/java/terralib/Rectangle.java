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

import com.google.common.base.Objects;

public final class Rectangle extends AbstractDataObject {

	private int left;
	private int right;
	private int top;
	private int bottom;

	public Rectangle() {
	}

	public Rectangle(int left, int right, int top, int bottom) {
		checkArgument(left >= 0 && left < right);
		checkArgument(top >= 0 && top < bottom);
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	protected Rectangle(DataInput input) throws IOException {
		parse(input);
	}

	@Override
	protected final void parse(DataInput input) throws IOException {
		left = input.readInt();
		right = input.readInt();
		top = input.readInt();
		bottom = input.readInt();

		if (left < 0 || top < 0)
			throw new MapParsingException(this, "< 0");
		if (left > right)
			throw new MapParsingException(this, "left > right");
		if (top > bottom)
			throw new MapParsingException(this, "top > bottom");
	}

	@Override
	protected final void write(DataOutput output) throws IOException {
		output.writeInt(left);
		output.writeInt(right);
		output.writeInt(top);
		output.writeInt(bottom);
	}

	public final boolean contains(Position position) {
		checkNotNull(position);

		return left <= position.getX() && position.getX() < right && top <= position.getY() && position.getY() < bottom;
	}

	public final boolean contains(Rectangle rect) {
		checkNotNull(rect);

		return left <= rect.left && rect.right <= right && bottom <= rect.bottom && rect.top <= top;
	}

	public final Rectangle intersects(Rectangle rect) {
		checkNotNull(rect);

		if (rect.left > right || rect.right < left || rect.top > bottom || rect.bottom < top)
			return null;

		return new Rectangle(Math.max(left, rect.left), Math.min(right, rect.right), Math.max(top, rect.top), Math.min(bottom, rect.bottom));
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
