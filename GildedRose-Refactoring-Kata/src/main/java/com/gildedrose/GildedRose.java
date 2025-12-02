package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (checkIsNotEqualTo(new String[] {"Aged Brie", "Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros"}, items[i]) && items[i].quality > 0) {
                items[i].decrementQuality();
            } else {
                if (items[i].quality < 50) {
                    items[i].incrementQuality();

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert") && items[i].quality < 50) {
                        if (items[i].sellIn < 11) {
                            items[i].incrementQuality();
                        }
                        if (items[i].sellIn < 6 ) {
                            items[i].incrementQuality();
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].decrementSellIn();
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0 && !items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                            items[i].decrementQuality();
                        }
                    } else {
                        items[i].quality = 0;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].incrementQuality();
                    }
                }
            }
        }
    }
    public boolean checkIsNotEqualTo(String[] listeString , Item item) {
        for (int i = 0; i < listeString.length; i++) {
            if (item.name.equals(listeString[i])){
                return false;
            }
        }
        return true;
    }

}
