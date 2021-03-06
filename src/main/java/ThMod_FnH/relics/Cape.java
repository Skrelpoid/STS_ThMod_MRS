package ThMod_FnH.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;

import ThMod_FnH.ThMod;
import basemod.abstracts.CustomRelic;

public class Cape extends CustomRelic {

  public static final String ID = "Cape";
  private static final String IMG = "img/relics/vCore.png";
  private static final String IMG_OTL = "img/relics/outline/vCore.png";
  private static final int RELIC_RATE = 30;
  private static final int RELIC_RATE_RARE = 18;
  private static final int RELIC_RATE_UNCOMMON = 50;

  public Cape() {
    super(
        ID,
        ImageMaster.loadImage(IMG),
        ImageMaster.loadImage(IMG_OTL),
        RelicTier.RARE,
        LandingSound.MAGICAL
    );
  }

  public void onEnterRoom(AbstractRoom room) {
    if ((room instanceof ShopRoom)) {
      ThMod.logger.info("Cape : onEnterRoom : ShopRoom detected .");
      this.flash();
      AbstractRoom currRoom = AbstractDungeon.getCurrRoom();
      currRoom.addPotionToRewards();
      if (AbstractDungeon.relicRng.random(0, 99) < RELIC_RATE) {
        RelicTier tier = RelicTier.COMMON;
        int rate = AbstractDungeon.relicRng.random(0, 99);
        if (rate < RELIC_RATE_RARE) {
          tier = RelicTier.RARE;
        }
        if (rate < RELIC_RATE_UNCOMMON) {
          tier = RelicTier.UNCOMMON;
        }
        currRoom.addRelicToRewards(tier);
      }
      AbstractDungeon.combatRewardScreen.open();
    }
  }

  public String getUpdatedDescription() {
    return DESCRIPTIONS[0];
  }

  public AbstractRelic makeCopy() {
    return new Cape();
  }
}