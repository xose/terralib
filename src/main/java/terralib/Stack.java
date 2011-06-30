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

import terralib.enums.ItemType;
import terralib.util.CSharpData;

import com.google.common.base.Objects;

public final class Stack extends AbstractDataObject {

	private ItemType itemType = ItemType.Nothing;
	private int size;

	public Stack() {
	}

	public Stack(ItemType item, int size) {
		setItemType(item);
		setSize(size);
	}

	@Override
	protected final void parse(DataInput input) throws IOException {
		size = input.readUnsignedByte();
		if (size == 0) {
			itemType = ItemType.Nothing;
			return;
		}

		itemType = ItemType.fromString(CSharpData.readString(input));

		// FIXME: Generated chests have wrong stack sizes (!)
		// if (size > itemType.getMaxStack())
		// throw new MapParsingException(this, "Stack too big for item " +
		// itemType.toString() + " (" + size + " > " + itemType.getMaxStack() +
		// ")");
	}

	@Override
	protected final void write(DataOutput output) throws IOException {
		output.writeByte(size);
		if (size > 0) {
			CSharpData.writeString(output, itemType.toString());
		}
	}

	public final ItemType getItemType() {
		return itemType;
	}

	public final void setItemType(ItemType itemType) {
		this.itemType = checkNotNull(itemType);

		if (size > itemType.getMaxStack()) {
			size = itemType.getMaxStack();
		}
	}

	public final int getSize() {
		return size;
	}

	public final void setSize(int size) {
		checkArgument(size >= 0 && size <= itemType.getMaxStack());
		this.size = size;
	}

	@Override
	public final String toString() {
		return Objects.toStringHelper(this).add("Size", Integer.valueOf(size)).add("Item", itemType).toString();
	}
}
