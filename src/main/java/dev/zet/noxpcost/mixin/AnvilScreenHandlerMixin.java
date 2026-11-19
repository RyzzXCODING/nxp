package dev.zet.noxpcost.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Mencegat AnvilScreenHandler#onTakeOutput, yaitu method yang dipanggil
 * tepat ketika pemain mengambil hasil item dari anvil. Di dalam method
 * itu game normalnya memanggil player.addExperienceLevels(-levelCost)
 * untuk mengurangi XP sesuai biaya perbaikan/rename/gabung item.
 *
 * Mixin ini me-redirect pemanggilan addExperienceLevels() HANYA di
 * dalam method onTakeOutput milik AnvilScreenHandler menjadi no-op,
 * jadi XP pemain tidak berkurang sama sekali, tapi hasil item tetap
 * keluar dan efek/sound anvil tetap jalan seperti biasa.
 */
@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {

    @Redirect(
            method = "onTakeOutput",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;addExperienceLevels(I)V"
            )
    )
    private void noxpcost$cancelAnvilXpCost(PlayerEntity player, int levels) {
        // Sengaja dikosongkan: batalkan pengurangan level XP dari anvil.
    }
}
