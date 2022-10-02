package ExtraDifficulties.patches;

import AscensionExtra.AscensionMod;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;

import static ExtraDifficulties.ExtraDifficultiesMod.makeID;


public class HardshipsPatch {

    @SpirePatch2(clz = Exordium.class, method = "generateWeakEnemies")
    public static class ExordiumEnemies {

        @SpirePrefixPatch
        public static void harderPools(@ByRef Integer[] count) {
            if (AscensionMod.isLvlActive(makeID("Hardships"), 1)) count[0]--;
            if (AscensionMod.isLvlActive(makeID("Hardships"), 4)) count[0]--;
        }
    }

    @SpirePatch2(clz = TheCity.class, method = "generateWeakEnemies")
    public static class THeCityEnemies {

        @SpirePrefixPatch
        public static void harderPools(@ByRef Integer[] count) {
            if (AscensionMod.isLvlActive(makeID("Hardships"), 2)) count[0]--;
        }
    }

    @SpirePatch2(clz = TheBeyond.class, method = "generateWeakEnemies")
    public static class TheBeyondEnemies {

        @SpirePrefixPatch
        public static void harderPools(@ByRef Integer[] count) {
            if (AscensionMod.isLvlActive(makeID("Hardships"), 3)) count[0]--;
        }
    }
}
