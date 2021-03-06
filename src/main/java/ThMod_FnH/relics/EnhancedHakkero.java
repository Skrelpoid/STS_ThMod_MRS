package ThMod_FnH.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import ThMod_FnH.ThMod;
import ThMod_FnH.powers.Marisa.ChargeUpPower;
import basemod.abstracts.CustomRelic;

public class EnhancedHakkero extends CustomRelic {

  public static final String ID = "EnhancedHakkero";
  private static final String IMG = "img/relics/Hakkero_1_s.png";
  private static final String IMG_OTL = "img/relics/outline/Hakkero_1_s.png";

  public EnhancedHakkero() {
    super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS,
        LandingSound.MAGICAL);
  }

  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }

  public AbstractRelic makeCopy() {
    return new EnhancedHakkero();
  }

  public void obtain() {
    if (AbstractDungeon.player.hasRelic("MiniHakkero")) {
      instantObtain(AbstractDungeon.player, 0, false);
    } else {
      super.obtain();
    }
  }

  public void onUseCard(AbstractCard card, UseCardAction action) {
    flash();
    ThMod.logger.info("Applying ChargeUpPower");
    AbstractDungeon.actionManager.addToTop(
        new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
            new ChargeUpPower(AbstractDungeon.player, 2), 2));
    AbstractDungeon.actionManager
        .addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
  }

  public void atTurnStart() {
    AbstractDungeon.actionManager.addToTop(
        new MakeTempCardInHandAction(new Burn(), 1)
    );
  }
}