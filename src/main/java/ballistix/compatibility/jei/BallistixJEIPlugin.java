package ballistix.compatibility.jei;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ballistix.compatibility.jei.recipecategories.psuedo.specificmachines.WarheadRecipeCategory;
import ballistix.compatibility.jei.util.psuedorecipes.BallistixPsuedoRecipes;
import electrodynamics.compatibility.jei.recipecategories.psuedo.PsuedoItem2ItemRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class BallistixJEIPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
	return new ResourceLocation(electrodynamics.api.References.ID, "balx_jei_plugin");
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
	// Warhead Template
	registration.addRecipeCatalyst(WarheadRecipeCategory.INPUT_MACHINE, WarheadRecipeCategory.UID);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
	BallistixPsuedoRecipes.addBallistixRecipes();

	// Warhead Template
	Set<PsuedoItem2ItemRecipe> warheadTemplateRecipes = new HashSet<>(BallistixPsuedoRecipes.WARHEAD_RECIPES);
	registration.addRecipes(warheadTemplateRecipes, WarheadRecipeCategory.UID);

	ballistixInfoTabs(registration);

    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
	// Warhead Template
	registration.addRecipeCategories(new WarheadRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    private static void ballistixInfoTabs(IRecipeRegistration registration) {
	/*
	 * Items currently with tabs:
	 * 
	 * Close Range Missile Medium Range Missile Long Range Missile Missile Silo
	 * 
	 */
	ArrayList<ItemStack> ballistixInfoItems = BallistixPsuedoRecipes.BALLISTIX_ITEMS.get(1);
	String temp;

	for (ItemStack itemStack : ballistixInfoItems) {
	    temp = itemStack.getItem().toString();
	    registration.addIngredientInfo(itemStack, VanillaTypes.ITEM, new TranslatableComponent("info.jei.item." + temp));
	}

	ballistixInfoItems = BallistixPsuedoRecipes.BALLISTIX_ITEMS.get(2);

	for (ItemStack itemStack : ballistixInfoItems) {
	    temp = itemStack.getItem().toString();
	    registration.addIngredientInfo(itemStack, VanillaTypes.ITEM, new TranslatableComponent("info.jei.item." + temp));
	}

    }

}