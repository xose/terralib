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

package terralib.enums;

public enum NpcType {
	Merchant("Merchant"),
	Nurse("Nurse"),
	ArmsDealer("Arms Dealer"),
	Dryad("Dryad"),
	Guide("Guide"),
	OldMan("Old Man"),
	Demolitionist("Demolitionist"),
	Clothier("Clothier");

	public static final NpcType fromString(String string) {
		for (NpcType type : values()) {
			if (type.toString().equals(string))
				return type;
		}

		throw new IllegalArgumentException("Unknown NPC: " + string);
	}

	private final String name;

	private NpcType(String name) {
		this.name = name;
	}

	@Override
	public final String toString() {
		return name;
	}
}
