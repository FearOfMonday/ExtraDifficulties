package ExtraDifficulties.patches;

import AscensionExtra.AscensionMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.WeakPower;

import static ExtraDifficulties.ExtraDifficultiesMod.makeID;
import static ExtraDifficulties.ExtraDifficultiesMod.TEXT;

public class WeakPlusPatch {

    @SpirePatch2(clz = WeakPower.class, method = "atDamageGive")
    public static class WeakPlus {

        @SpirePrefixPatch
        public static void weaker(WeakPower __instance, @ByRef Float[] damage, DamageInfo.DamageType type) {
            if (type == DamageInfo.DamageType.NORMAL && __instance.owner.isPlayer && AscensionMod.isLvlActive(makeID("MoreWeakened"), 1)) {
                damage[0] *= (1.0F - (0.1F * AscensionMod.getAscensionLvl(makeID("MoreWeakened"))));
            }
        }
    }

    @SpirePatch2(clz = WeakPower.class, method = SpirePatch.CONSTRUCTOR)
    public static class NameModifier {

        @SpirePostfixPatch
        public static void namer(WeakPower __instance) {
            if (__instance.owner.isPlayer && AscensionMod.isLvlActive(makeID("MoreWeakened"), 1)) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < AscensionMod.getAscensionLvl(makeID("MoreWeakened")); i++) sb.append(TEXT[0]);
                __instance.name += sb.toString();
            }
        }
    }

    @SpirePatch2(clz = WeakPower.class, method = "updateDescription")
    public static class DescriptionModifier {

        @SpirePostfixPatch
        public static void descripter(WeakPower __instance) {
            if (__instance.owner.isPlayer && AscensionMod.isLvlActive(makeID("MoreWeakened"), 1)) {
                if (__instance.amount == 1) {
                    __instance.description = TEXT[3] + (25f + (7.5f * AscensionMod.getAscensionLvl(makeID("MoreWeakened")))) + TEXT[4] + __instance.amount + TEXT[7];
                } else {
                    __instance.description = TEXT[3] + (25f + (7.5f * AscensionMod.getAscensionLvl(makeID("MoreWeakened")))) + TEXT[4] + __instance.amount + TEXT[8];
                }
            }
        }
    }
}
