package ThMod_FnH.cards.Marisa;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

import ThMod_FnH.ThMod;
import ThMod_FnH.abstracts.AmplifiedAttack;
import ThMod_FnH.action.SparkCostAction;
import ThMod_FnH.patches.AbstractCardEnum;
import basemod.helpers.BaseModTags;
import basemod.helpers.CardTags;

public class MasterSpark
    extends AmplifiedAttack {

  public static final String ID = "MasterSpark";
  private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
  public static final String NAME = cardStrings.NAME;
  public static final String DESCRIPTION = cardStrings.DESCRIPTION;
  public static final String IMG_PATH = "img/cards/MasterSpark.png";

  private static final int COST = 1;
  private static final int ATK_DMG = 9;
  private static final int UPG_DMG = 3;
  private static final int AMP_DMG = 7;
  private static final int UPG_AMP = 2;
  private static final int AMP = 1;


  public MasterSpark() {
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
    CardTags.addTags(this, BaseModTags.GREMLIN_MATCH);
    this.baseDamage = ATK_DMG;
    this.ampNumber = AMP_DMG;
    this.baseBlock = this.baseDamage + this.ampNumber;
  }

  public void use(AbstractPlayer p, AbstractMonster m) {

    AbstractDungeon.actionManager.addToBottom(
        new VFXAction(
            new MindblastEffect(p.dialogX, p.dialogY, false)
        )
    );

    AbstractDungeon.actionManager.addToBottom(
        new SparkCostAction()
    );
    if (ThMod.Amplified(this, AMP)) {
      AbstractDungeon.actionManager.addToBottom(
          new DamageAction(
              m,
              new DamageInfo(p, this.block, this.damageTypeForTurn),
              AbstractGameAction.AttackEffect.SLASH_DIAGONAL
          )
      );
    } else {
      AbstractDungeon.actionManager.addToBottom(
          new DamageAction(
              m,
              new DamageInfo(p, this.damage, this.damageTypeForTurn),
              AbstractGameAction.AttackEffect.SLASH_DIAGONAL
          )
      );
    }
  }

  public AbstractCard makeCopy() {
    return new MasterSpark();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeDamage(UPG_DMG);
      this.ampNumber += UPG_AMP;
      this.block = this.baseDamage + this.ampNumber;
      this.isBlockModified = true;
    }
  }
}