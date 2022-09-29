package ExtraDifficulties.patches;

import AscensionExtra.buttons.AscensionManager;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static ExtraDifficulties.ExtraDifficultiesMod.TEXT;
import static ExtraDifficulties.ExtraDifficultiesMod.makeID;

public class VulnerablePlusPatch {

    @SpirePatch2(clz = VulnerablePower.class, method = "atDamageReceive")
    public static class ActuallyVulnerabler {

        @SpirePrefixPatch
        public static void vulnerabler(VulnerablePower __instance, @ByRef Float[] damage, DamageInfo.DamageType type) {
            if (type == DamageInfo.DamageType.NORMAL && __instance.owner.isPlayer && AscensionManager.getAscensionLvl(makeID("MoreVulnerable")) >= 1) {
                damage[0] *= (1.0F + (0.1F * AscensionManager.getAscensionLvl(makeID("MoreVulnerable"))));
            }
        }
    }

    @SpirePatch2(clz = VulnerablePower.class, method = SpirePatch.CONSTRUCTOR)
    public static class NameModifier {

        @SpirePostfixPatch
        public static void namer(VulnerablePower __instance) {
            if (__instance.owner.isPlayer && AscensionManager.getAscensionLvl(makeID("MoreVulnerable")) >= 1) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < AscensionManager.getAscensionLvl(makeID("MoreVulnerable")); i++) sb.append(TEXT[0]);
                __instance.name += sb.toString();
            }
        }
    }

    @SpirePatch2(clz = VulnerablePower.class, method = "updateDescription")
    public static class DescriptionModifier {

        @SpirePostfixPatch
        public static void descripter(VulnerablePower __instance) {
            if (__instance.owner.isPlayer && AscensionManager.getAscensionLvl(makeID("MoreVulnerable")) >= 1) {
                if (__instance.amount == 1 && AbstractDungeon.player.hasRelic("Odd Mushroom")) {
                    __instance.description = TEXT[5] + (25f + (7.5f * AscensionManager.getAscensionLvl(makeID("MoreVulnerable")))) + TEXT[6] + __instance.amount + TEXT[7];
                } else if (__instance.amount == 1 && !AbstractDungeon.player.hasRelic("Odd Mushroom")) {
                    __instance.description = TEXT[5] + (50 + (15 * AscensionManager.getAscensionLvl(makeID("MoreVulnerable")))) + TEXT[6] + __instance.amount + TEXT[7];
                } else if (AbstractDungeon.player.hasRelic("Odd Mushroom")) {
                    __instance.description = TEXT[5] + (25f + (7.5f * AscensionManager.getAscensionLvl(makeID("MoreVulnerable")))) + TEXT[6] + __instance.amount + TEXT[8];
                } else {
                    __instance.description = TEXT[5] + (50 + (15 * AscensionManager.getAscensionLvl(makeID("MoreVulnerable")))) + TEXT[6] + __instance.amount + TEXT[8];
                }
            }
        }
    }
}
