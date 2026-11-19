package dev.zet.noxpcost;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * No XP Cost
 * Menghilangkan pengurangan experience level saat menggunakan anvil
 * dan enchanting table. Semua logika "nol-kan biaya XP" ada di dua
 * mixin: AnvilScreenHandlerMixin dan EnchantmentScreenHandlerMixin.
 *
 * Dibuat oleh Alpoyy.
 */
public class NoXpCostMod implements ModInitializer {

    public static final String MOD_ID = "noxpcost";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[No XP Cost] Aktif — anvil & enchanting table tidak lagi memakan XP.");
    }
}
