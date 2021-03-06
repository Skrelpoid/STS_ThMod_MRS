package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ThMod_FnH.action.SparkCostAction;
import ThMod_FnH.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModTags;
import basemod.helpers.CardTags;

public class Strike_MRS
    extends CustomCard {

  public static final String ID = "Strike_MRS";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/SimpleSpark.png";
  private static final int COST = 1;
  private static final int ATTACK_DMG = 6;
  private static final int UPGRADE_PLUS_DMG = 3;

  public Strike_MRS() {
    super(
        ID,
        NAME,
        IMG_PATH,
        COST,
        DESCRIPTION,
        AbstractCard.CardType.ATTACK,
        AbstractCardEnum.MARISA_COLOR,
        AbstractCard.CardRarity.BASIC,
        AbstractCard.CardTarget.ENEMY
    );
    CardTags.addTags(this, BaseModTags.BASIC_STRIKE);
    this.baseDamage = ATTACK_DMG;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {
    AbstractDungeon.actionManager.addToBottom(
        new DamageAction(
            m,
            new DamageInfo(p, this.damage, this.damageTypeForTurn),
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        )
    );
    AbstractDungeon.actionManager.addToBottom(
        new SparkCostAction()
    );
  }

  public AbstractCard makeCopy() {
    return new Strike_MRS();
  }

  @Override
  public boolean isStrike() {
    return true;
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPGRADE_PLUS_DMG);
    }
  }
}