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

public final class TileGroup {

	private final Rectangle rect;
	private final Tile[] tileData;

	protected TileGroup(Rectangle rect, Tile[] tileData) {
		this.rect = checkNotNull(rect);
		checkArgument(tileData.length == rect.getWidth() * rect.getHeight());
		this.tileData = tileData;
	}

	public final Rectangle getRect() {
		return rect;
	}

	public final Tile getTile(Position pos) {
		checkArgument(rect.contains(pos));

		int inX = pos.getX() - rect.getLeft();
		int inY = pos.getY() - rect.getTop();

		return tileData[inX * rect.getHeight() + inY];
	}
}
