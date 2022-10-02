package ExtraDifficulties;

import AscensionExtra.AscensionMod;
import AscensionExtra.util.TexLoader;
import basemod.*;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class ExtraDifficultiesMod implements EditStringsSubscriber, PostInitializeSubscriber {

    public static final Logger LOGGER = LogManager.getLogger(ExtraDifficultiesMod.class.getName());
    public static final String MOD_ID = "ExtraDifficulties";
    public static final String AUTHOR = "FearOfMonday";
    public static final String DESCRIPTION = "A collection of extra Ascension modifiers, meant to mostly showcase how to use my Ascension Framework.";

    public static String makeID(String idText) {
        return MOD_ID + ":" + idText;
    }
    public static String makePath(String resourcePath) {
        return MOD_ID + "Resources/" + resourcePath;
    }
    public static final String BADGE_IMAGE = makePath("images/Badge.png");

    public static String[] TEXT;

    public ExtraDifficultiesMod() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new ExtraDifficultiesMod();
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(UIStrings.class, MOD_ID + "Resources/localization/eng/UIstrings.json");
    }

    @Override
    public void receivePostInitialize() {
        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("AscensionPowerModifiers"));
        TEXT = uiStrings.TEXT;
        UIStrings uiStrings1 = CardCrawlGame.languagePack.getUIString(makeID("AscensionNames"));
        String[] text = uiStrings1.TEXT;
        AscensionMod.addAscensionData(makePath("images/curseplus.png"), makeID("MoreCurses"), text[0], CardCrawlGame.languagePack.getUIString(makeID("CurseDescriptions")).TEXT, true);
        AscensionMod.addAscensionData(makePath("images/weakplus.png"), makeID("MoreWeakened"), text[1], CardCrawlGame.languagePack.getUIString(makeID("WeakDescriptions")).TEXT, true);
        AscensionMod.addAscensionData(makePath("images/frailplus.png"), makeID("MoreFrail"), text[2], CardCrawlGame.languagePack.getUIString(makeID("FrailDescriptions")).TEXT, true);
        AscensionMod.addAscensionData(makePath("images/vulnplus.png"), makeID("MoreVulnerable"), text[3], CardCrawlGame.languagePack.getUIString(makeID("VulnDescriptions")).TEXT, true);
        AscensionMod.addAscensionData(makePath("images/earlyhardship.png"), makeID("Hardships"), text[4], CardCrawlGame.languagePack.getUIString(makeID("HardshipsDesc")).TEXT, true);
        AscensionMod.registerAsUnlockable(makeID("MoreCurses"));
        AscensionMod.registerAsUnlockable(makeID("MoreWeakened"));
        AscensionMod.registerAsUnlockable(makeID("MoreFrail"));
        AscensionMod.registerAsUnlockable(makeID("MoreVulnerable"));
        AscensionMod.registerAsUnlockable(makeID("Hardships"));
        setupMenu();
    }

    private void setupMenu() {
        Texture badgeTexture = TexLoader.getTexture(BADGE_IMAGE);
        ModPanel settingsPanel = new ModPanel();
        ModLabel aLabel = new ModLabel(CardCrawlGame.languagePack.getUIString(makeID("ModSettings")).TEXT[0],
                465.0F, 530.0F, FontHelper.charDescFont, settingsPanel, label -> {});
        settingsPanel.addUIElement(aLabel);
        ModButton moderButton = new ModButton(350.0f, 475.0F, settingsPanel, button -> {
            AscensionMod.unlockAscension(makeID("Hardships"));
            AscensionMod.unlockAscension(makeID("MoreVulnerable"));
            AscensionMod.unlockAscension(makeID("MoreFrail"));
            AscensionMod.unlockAscension(makeID("MoreWeakened"));
            AscensionMod.unlockAscension(makeID("MoreCurses"));
        });
        settingsPanel.addUIElement(moderButton);

        BaseMod.registerModBadge(badgeTexture, MOD_ID, AUTHOR, DESCRIPTION, settingsPanel);
    }
}
