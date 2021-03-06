package ThMod_FnH.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ThMod_FnH.ThMod;

public class SuperNovaDiscardPatch {
	/*
	@SpirePatch(cls = "com.megacrit.cardcrawl.cards.status.Burn", method = "triggerOnEndOfTurnForPlayingCard")
	public static class DisableBurn_Replace {
		public static void Replace(AbstractCard _inst) {
			if (AbstractDungeon.player.hasPower("SuperNovaPower")) {
				ThMod.logger.info("SuperNovaPatch : Burn detected.");
				return;
			} else {
				_inst.dontTriggerOnUseCard = true;
				AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(_inst, true));
			}
		}
	}
	*/

  @SpirePatch(cls = "com.megacrit.cardcrawl.cards.status.Burn", method = "triggerOnEndOfTurnForPlayingCard")
  public static class DisableBurn_PreFix {

    @SuppressWarnings("rawtypes")
    @SpirePrefixPatch
    public static SpireReturn Prefix(Object _obj_instance) {
      if (AbstractDungeon.player.hasPower("SuperNovaPower")) {
        ThMod.logger.info("SuperNovaPatch : Burn detected.");
        return SpireReturn.Return(null);
      }
      return SpireReturn.Continue();
    }
  }
}
