package de.DaWik.DaWik.crafting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;

public class Workbench {
	public Workbench() {

	}

	public void load() {
		try {
			URL u = new URL("http://pastebin.com/raw.php?i=cJAP7iyR");
			URLConnection conn = u.openConnection();
			BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			ArrayList<BlPl> blocks = new ArrayList<BlPl>();
			ArrayList<ItPl> items = new ArrayList<ItPl>();

			while ((line = r.readLine()) != null) {
				if (!line.startsWith("#")) {
					if (line.contains("=")) {
						String[] tmp = line.split("=", 2);
						// test=test=test
						// [0]=test
						// [1]=test=test
						switch (tmp[0]) {
						case "item":
							String[] tmp2 = tmp[1].split("=", 2);

							items.add(new ItPl((Item) Item.itemRegistry.getObject(tmp2[0]), tmp2[1]));
							break;
						case "block":
							String[] tmp3 = tmp[1].split("=", 2);

							blocks.add(new BlPl((Block) Block.blockRegistry.getObject(tmp3[0]), tmp3[1]));
							break;
						case "shape":
							String[] tmp4 = tmp[1].split(";", 6);
							ArrayList<BlIt> reihenFolge = getUnique(tmp4, blocks, items);
							Object[] finalShape = new Object[5 + (reihenFolge.size() * 2)];
							finalShape[0] = tmp4[0];
							finalShape[1] = tmp4[1];
							finalShape[2] = tmp4[2];
							finalShape[3] = tmp4[3];
							finalShape[4] = tmp4[4];
							int count = 0;
							boolean bool = false;
							for (int i = 5; i < finalShape.length; i++) {
								BlIt iii = reihenFolge.get(count);

								if (!bool) {
									bool = true;
									finalShape[i] = iii.getPlace();
								} else {
									bool = false;
									count++;
									if (iii.getBlock() != null) {
										finalShape[i] = iii.getBlock();
									} else {
										finalShape[i] = iii.getItem();
									}
								}
							}
							BlPl itB = getBlock(tmp4[tmp4.length - 1], blocks);

							if (itB != null) {
								WorkSurfaceCraftingManager.get().addRecipe(new ItemStack(itB.getBlock()), finalShape);
							} else {
								ItPl itI = getItem(tmp4[tmp4.length - 1], items);
								WorkSurfaceCraftingManager.get().addRecipe(new ItemStack(itI.getItem()), finalShape);
							}

							break;
						}
					}
				}
			}
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private ArrayList<BlIt> getUnique(String[] str, ArrayList<BlPl> list, ArrayList<ItPl> list2) {
		ArrayList<BlIt> result = new ArrayList<BlIt>();

		for (String s : str) {
			for (char c : s.toCharArray()) {
				boolean contains = false;
				for (BlIt results : result) {
					if (results.getPlace() == c) {
						contains = true;
						break;
					}
				}
				if (!contains) {
					boolean contains2 = false;
					for (BlPl l : list) {
						if (l.getPlaceholderChar() == c) {
							result.add(new BlIt(l.getBlock(), c));
							contains2 = true;
							break;
						}
					}
					if (!contains2) {
						for (ItPl ll : list2) {
							if (ll.getPlaceholderChar() == c) {
								result.add(new BlIt(ll.getItem(), c));
							}
						}
					}
				}
			}
		}
		return result;
	}

	private BlPl getBlock(String place, ArrayList<BlPl> list) {
		for (BlPl p : list) {
			if (p.getPlaceholder().equals(place)) {
				return p;
			}
		}
		return null;
	}

	private ItPl getItem(String place, ArrayList<ItPl> list) {
		for (ItPl p : list) {
			if (p.getPlaceholder().equals(place)) {
				return p;
			}
		}
		return null;
	}

	public static void register() {
		ItemStack bookEf1 = new ItemStack(Items.enchanted_book);
		ItemEnchantedBook book1 = Items.enchanted_book;
		book1.addEnchantment(bookEf1, new EnchantmentData(Enchantment.efficiency, 1));
		// WorkSurfaceCraftingManager.get().addRecipe(bookEf1, "rrrrr", "rfffr",
		// "rfdfr", "rfffr", "rrrrr", 'r', Items.redstone, 'f', Items.feather,
		// 'd', Items.diamond);
		// WorkSurfaceCraftingManager.get().addRecipe(new
		// ItemStack(DaWikItems.pickaxeBreaker), "essse", "seses", "ssdss",
		// "seses", "essse", 'e', Blocks.iron_block, 's', Blocks.stone, 'd',
		// Items.diamond_pickaxe);
	}

	public class ItPl {
		private Item item;
		private String placeholder;

		public ItPl(Item item, String placeholder) {
			this.item = item;
			this.placeholder = placeholder;
		}

		public Item getItem() {
			return item;
		}

		public String getPlaceholder() {
			return placeholder;
		}

		public char getPlaceholderChar() {
			return placeholder.toCharArray()[0];
		}

	}

	public class BlPl {
		private Block block;
		private String placeholder;

		public BlPl(Block block, String placeholder) {
			this.block = block;
			this.placeholder = placeholder;
		}

		public Block getBlock() {
			return block;
		}

		public String getPlaceholder() {
			return placeholder;
		}

		public char getPlaceholderChar() {
			return placeholder.toCharArray()[0];
		}

	}

	public class BlIt {
		private Item item;
		private Block block;
		private char place;

		public BlIt(Block block, char place) {
			item = null;
			this.block = block;
			this.place = place;
		}

		public BlIt(Item item, char place) {
			this.item = item;
			block = null;
			this.place = place;
		}

		public Item getItem() {
			return item;
		}

		public Block getBlock() {
			return block;
		}

		public char getPlace() {
			return place;
		}

	}
}
