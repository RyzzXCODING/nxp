package dev.zet.noxpcost.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.EnchantmentScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Mencegat EnchantmentScreenHandler#onButtonClick, yaitu method yang
 * dipanggil ketika pemain memilih salah satu dari 3 pilihan enchant
 * di enchanting table. Normalnya di sinilah XP pemain dikurangi sesuai
 * level enchant yang dipilih (lewat player.addExperienceLevels(-cost)).
 *
 * Mixin ini me-redirect pemanggilan addExperienceLevels() HANYA di
 * dalam method onButtonClick milik EnchantmentScreenHandler menjadi
 * no-op, jadi enchant tetap diterapkan ke item tapi XP pemain tidak
 * berkurang, dan lapis lazuli tetap terpakai seperti biasa.
 */
@Mixin(EnchantmentScreenHandler.class)
public class EnchantmentScreenHandlerMixin {

    @Redirect(
            method = "onButtonClick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;addExperienceLevels(I)V"
            )
    )
    private void noxpcost$cancelEnchantXpCost(PlayerEntity player, int levels) {
        // Sengaja dikosongkan: batalkan pengurangan level XP dari enchanting table.
    }
}
