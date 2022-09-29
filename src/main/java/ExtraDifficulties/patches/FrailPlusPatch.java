package ExtraDifficulties.patches;

import AscensionExtra.buttons.AscensionManager;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.powers.FrailPower;

import static ExtraDifficulties.ExtraDifficultiesMod.TEXT;
import static ExtraDifficulties.ExtraDifficultiesMod.makeID;

public class FrailPlusPatch {

    @SpirePatch2(clz = FrailPower.class, method = "modifyBlock")
    public static class ActuallyFrailer {

        @SpirePrefixPatch
        public static void frailer(@ByRef Float[] blockAmount) {
            if (AscensionManager.getAscensionLvl(makeID("MoreFrail")) >= 1) {
                blockAmount[0] *= (1.0F - (0.1F * AscensionManager.getAscensionLvl(makeID("MoreFrail"))));
            }
        }
    }

    @SpirePatch2(clz = FrailPower.class, method = SpirePatch.CONSTRUCTOR)
    public static class NameModifier {

        @SpirePostfixPatch
        public static void namer(FrailPower __instance) {
            if (AscensionManager.getAscensionLvl(makeID("MoreFrail")) >= 1) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < AscensionManager.getAscensionLvl(makeID("MoreFrail")); i++) sb.append(TEXT[0]);
                __instance.name += sb.toString();
            }
        }
    }

    @SpirePatch2(clz = FrailPower.class, method = "updateDescription")
    public static class DescriptionModifier {

        @SpirePostfixPatch
        public static void descripter(FrailPower __instance) {
            if (AscensionManager.getAscensionLvl(makeID("MoreFrail")) >= 1) {
                if (__instance.amount == 1) {
                    __instance.description = TEXT[1] + (25f + (7.5f * AscensionManager.getAscensionLvl(makeID("MoreFrail")))) + TEXT[2] + __instance.amount + TEXT[7];
                } else {
                    __instance.description = TEXT[1] + (25f + (7.5f * AscensionManager.getAscensionLvl(makeID("MoreFrail")))) + TEXT[2] + __instance.amount + TEXT[8];
                }
            }
        }
    }
}
