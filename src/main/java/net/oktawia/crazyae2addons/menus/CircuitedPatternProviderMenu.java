package net.oktawia.crazyae2addons.menus;

import appeng.api.config.LockCraftingMode;
import appeng.api.config.Settings;
import appeng.api.config.YesNo;
import appeng.api.stacks.GenericStack;
import appeng.helpers.externalstorage.GenericStackInv;
import appeng.helpers.patternprovider.PatternProviderLogic;
import appeng.helpers.patternprovider.PatternProviderLogicHost;
import appeng.helpers.patternprovider.PatternProviderReturnInventory;
import appeng.menu.AEBaseMenu;
import appeng.menu.SlotSemantics;
import appeng.menu.guisync.GuiSync;
import appeng.menu.implementations.MenuTypeBuilder;
import appeng.menu.implementations.PatternProviderMenu;
import appeng.menu.slot.AppEngSlot;
import appeng.menu.slot.RestrictedInputSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.oktawia.crazyae2addons.defs.Menus;
import net.oktawia.crazyae2addons.logic.CircuitedPatternProviderLogic;
import net.oktawia.crazyae2addons.logic.CircuitedPatternProviderLogicHost;

public class CircuitedPatternProviderMenu extends AEBaseMenu {

    protected final CircuitedPatternProviderLogic logic;

    @GuiSync(36)
    public YesNo blockingMode = YesNo.NO;
    @GuiSync(46)
    public YesNo showInAccessTerminal = YesNo.YES;
    @GuiSync(56)
    public LockCraftingMode lockCraftingMode = LockCraftingMode.NONE;
    @GuiSync(66)
    public LockCraftingMode craftingLockedReason = LockCraftingMode.NONE;
    @GuiSync(76)
    public GenericStack unlockStack = null;

    public CircuitedPatternProviderMenu(int id, Inventory playerInventory, CircuitedPatternProviderLogicHost host) {
        super(Menus.CIRCUITED_PATTERN_PROVIDER_MENU, id, playerInventory, host);
        this.createPlayerInventorySlots(playerInventory);
        this.logic = host.getLogic();
        var patternInv = logic.getPatternInv();
        for (int x = 0; x < patternInv.size(); x++) {
            this.addSlot(new RestrictedInputSlot(RestrictedInputSlot.PlacableItemType.ENCODED_PATTERN,
                            patternInv, x),
                    SlotSemantics.ENCODED_PATTERN);
        }

        var returnInv = logic.getReturnInv().createMenuWrapper();
        for (int i = 0; i < PatternProviderReturnInventory.NUMBER_OF_SLOTS; i++) {
            if (i < returnInv.size()) {
                this.addSlot(new AppEngSlot(returnInv, i), SlotSemantics.STORAGE);
            }
        }
    }


    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
    }

    public GenericStackInv getReturnInv() {
        return logic.getReturnInv();
    }

    public YesNo getBlockingMode() {
        return blockingMode;
    }

    public LockCraftingMode getLockCraftingMode() {
        return lockCraftingMode;
    }

    public LockCraftingMode getCraftingLockedReason() {
        return craftingLockedReason;
    }

    public GenericStack getUnlockStack() {
        return unlockStack;
    }

    public YesNo getShowInAccessTerminal() {
        return showInAccessTerminal;
    }
}
