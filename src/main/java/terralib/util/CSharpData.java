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

package terralib.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

// Mostly copy-pasted from Mono (System.IO.BinaryReader)
// Simplified String conversion (no chunks)
// Copyright (C) 2004 Novell, Inc (http://www.novell.com)
public class CSharpData {

	private static final int read7BitInteger(DataInput input) throws IOException {
		int ret = 0;
		int shift = 0;
		int len;
		byte b;

		for (len = 0; len < 5; ++len) {
			b = input.readByte();

			ret = ret | (b & 0x7f) << shift;
			shift += 7;
			if ((b & 0x80) == 0) {
				break;
			}
		}

		if (len >= 5)
			throw new IOException("Too many bytes in what should have been a 7 bit encoded Int32.");

		return ret;
	}

	private static final void write7BitInteger(DataOutput output, int value) throws IOException {
		do {
			int high = value >> 7 & 0x01ffffff;
			byte b = (byte) (value & 0x7f);

			if (high != 0) {
				b = (byte) (b | 0x80);
			}

			output.writeByte(b);
			value = high;
		} while (value != 0);
	}

	public static final String readString(DataInput input) throws IOException {
		int len = read7BitInteger(input);

		if (len < 0)
			throw new IOException("Invalid binary file (string len < 0)");
		if (len == 0)
			return "";

		byte charByteBuffer[] = new byte[len];
		input.readFully(charByteBuffer);

		return new String(charByteBuffer);
	}

	public static final void writeString(DataOutput output, String value) throws IOException {
		int len = value.getBytes().length;
		write7BitInteger(output, len);
		output.write(value.getBytes());
	}

}
