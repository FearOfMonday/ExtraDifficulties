package ExtraDifficulties.cardMods;

import ExtraDifficulties.ExtraDifficultiesMod;
import basemod.abstracts.AbstractCardModifier;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class SoulBoundMod extends AbstractCardModifier {

    @Override
    public void onInitialApplication(AbstractCard card) {
        SoulboundField.soulbound.set(card, true);
    }

    @Override
    public void onRemove(AbstractCard card) {
        SoulboundField.soulbound.set(card, false);
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + " NL " + ExtraDifficultiesMod.TEXT[9];
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new SoulBoundMod();
    }
}
