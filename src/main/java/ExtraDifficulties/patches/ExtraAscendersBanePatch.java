package ExtraDifficulties.patches;

import AscensionExtra.buttons.AscensionManager;
import ExtraDifficulties.cardMods.SoulBoundMod;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.AscendersBane;
import com.megacrit.cardcrawl.cards.curses.Pride;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javassist.CtBehavior;

import static ExtraDifficulties.ExtraDifficultiesMod.makeID;

public class ExtraAscendersBanePatch {

    @SpirePatch2(clz = AbstractDungeon.class, method = "dungeonTransitionSetup")
    public static class ExtraAscendersBane {

        @SpireInsertPatch(locator = Locator.class)
        public static void bane() {
            if (AscensionManager.getAscensionLvl(makeID("MoreCurses")) == 1) {
                AbstractDungeon.player.masterDeck.addToTop(new AscendersBane());
                UnlockTracker.markCardAsSeen("AscendersBane");
            }
            if (AscensionManager.getAscensionLvl(makeID("MoreCurses")) >= 2) {
                AbstractDungeon.player.masterDeck.removeCard("AscendersBane");
                AbstractCard card = new Pride();
                CardModifierManager.addModifier(card, new SoulBoundMod());
                AbstractDungeon.player.masterDeck.addToTop(card);
                UnlockTracker.markCardAsSeen("Pride");
            }
        }
        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.FieldAccessMatcher matcher = new Matcher.FieldAccessMatcher(CardCrawlGame.class, "playtime");
                return LineFinder.findInOrder(ctMethodToPatch, matcher);
            }
        }
    }
}
