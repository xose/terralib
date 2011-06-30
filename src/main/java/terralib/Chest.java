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

import static com.google.common.base.Preconditions.checkElementIndex;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

public final class Chest extends AbstractDataObject {

	private final Stack[] items = new Stack[20];

	public Chest() {
		for (int i = 0; i < items.length; i++) {
			items[i] = new Stack();
		}
	}

	protected Chest(DataInput input) throws IOException {
		this();
		parse(input);
	}

	@Override
	protected final void parse(DataInput input) throws IOException {
		for (Stack item : items) {
			item.parse(input);
		}
	}

	@Override
	protected final void write(DataOutput output) throws IOException {
		for (Stack item : items) {
			item.write(output);
		}
	}

	public final Stack getStack(int idx) {
		checkElementIndex(idx, items.length);
		return items[idx];
	}

	public final int getSize() {
		return items.length;
	}

	@Override
	public final String toString() {
		ToStringHelper helper = Objects.toStringHelper(this);

		for (int i = 0; i < items.length; i++) {
			Stack stack = items[i];
			if (stack.getSize() > 0) {
				helper.add("[" + i + "]", stack);
			}
		}

		return helper.toString();
	}
}
