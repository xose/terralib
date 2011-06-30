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

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteBufferInputStream extends InputStream {

	private final ByteBuffer byteBuffer;

	public ByteBufferInputStream(ByteBuffer byteBuffer) {
		super();
		this.byteBuffer = byteBuffer;
	}

	@Override
	public int available() {
		return byteBuffer.remaining();
	}

	@Override
	public int read() throws IOException {
		if (!byteBuffer.hasRemaining())
			return -1;

		return byteBuffer.get() & 0xff;
	}

	@Override
	public int read(byte[] b) throws IOException {
		return read(b, 0, b.length);
	}

	@Override
	public int read(byte b[], int off, int len) throws IOException {
		if (!byteBuffer.hasRemaining())
			return -1;

		if (len > available()) {
			len = available();
		}

		byteBuffer.get(b, off, len);

		return len;
	}

	@Override
	public long skip(long n) throws IOException {
		if (n <= 0)
			return 0;
		else if (n > available()) {
			n = available();
		}

		byteBuffer.position((int) (byteBuffer.position() + n));

		return n;
	}
}
