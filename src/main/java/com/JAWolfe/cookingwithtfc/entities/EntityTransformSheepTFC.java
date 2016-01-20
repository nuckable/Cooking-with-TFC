package com.JAWolfe.cookingwithtfc.entities;

import java.util.ArrayList;
import java.util.List;

import com.JAWolfe.cookingwithtfc.init.Items.CWTFCItems;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Entities.Mobs.EntitySheepTFC;
import com.bioxx.tfc.api.Entities.IAnimal;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityTransformSheepTFC extends EntitySheepTFC
{
	private float mateSizeModCWTFC;

	public EntityTransformSheepTFC(World par1World) 
	{
		super(par1World);
		this.tasks.addTask(3, new EntityAITempt(this, 1.2F, CWTFCItems.wheatGrainCWTFC, false));
		this.tasks.addTask(3, new EntityAITempt(this, 1.2F, CWTFCItems.ryeGrainCWTFC, false));
		this.tasks.addTask(3, new EntityAITempt(this, 1.2F, CWTFCItems.riceGrainCWTFC, false));
		this.tasks.addTask(3, new EntityAITempt(this, 1.2F, CWTFCItems.barleyGrainCWTFC, false));
		this.tasks.addTask(3, new EntityAITempt(this, 1.2F, CWTFCItems.oatGrainCWTFC, false));
		this.tasks.addTask(3, new EntityAITempt(this, 1.2F, CWTFCItems.maizeEarCWTFC, false));
		this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityTransformWolfTFC.class, 8f, 1.0F, 1.2F));
		this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityTransformBear.class, 16f, 1.0F, 1.2F));
		
		mateSizeModCWTFC = 0;
		
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400 * this.getSizeMod() * this.getStrengthMod());
		this.setHealth(this.getMaxHealth());
	}
	
	public EntityTransformSheepTFC(World par1World, IAnimal mother, List<Float> data)
	{
		super(par1World, mother, data);
		
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400 * this.getSizeMod() * this.getStrengthMod());
		this.setHealth(this.getMaxHealth());
	}
	
	@Override
	public void mate(IAnimal otherAnimal)
	{
		super.mate(otherAnimal);
		mateSizeModCWTFC = otherAnimal.getSizeMod();
	}
	
	@Override
	public boolean canMateWith(IAnimal animal)
	{
		return animal.getGender() != this.getGender() &&this.isAdult() && animal.isAdult() &&
				animal instanceof EntityTransformSheepTFC;
	}
	
	@Override
	public EntityAgeable createChildTFC(EntityAgeable eAgeable)
	{
		ArrayList<Float> data = new ArrayList<Float>();
		data.add(eAgeable.getEntityData().getFloat("MateSize"));
		data.add(eAgeable.getEntityData().getFloat("MateStrength"));
		data.add(eAgeable.getEntityData().getFloat("MateAggro"));
		data.add(eAgeable.getEntityData().getFloat("MateObed"));
		return new EntityTransformSheepTFC(worldObj, this, data);
	}
	
	@Override
	public boolean isFood(ItemStack item)
	{
		return item != null && (item.getItem() == CWTFCItems.wheatGrainCWTFC ||item.getItem() == CWTFCItems.oatGrainCWTFC || item.getItem() == CWTFCItems.riceGrainCWTFC ||
								item.getItem() == CWTFCItems.barleyGrainCWTFC || item.getItem() == CWTFCItems.ryeGrainCWTFC || item.getItem() == CWTFCItems.maizeEarCWTFC);
	}
	
	@Override
	public void onLivingUpdate()
	{
		if (this.worldObj.isRemote)
			setSheepTimer(Math.max(0, getSheepTimer() - 1));

		//Handle Hunger ticking
		if (getHunger() > 168000)
			setHunger(168000);
		if (getHunger() > 0)
			setHunger(getHunger() - 1);

		if (super.isInLove())
		{
			super.resetInLove();
			setInLove(true);
		}

		this.handleFamiliarityUpdate();

		syncData();
		
		if (isAdult())
		{
			setGrowingAge(0);
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(400 * this.getSizeMod() * this.getStrengthMod());
		}
		else
		{
			float maxBaseHealth = 400 * this.getSizeMod() * this.getStrengthMod();
			float growthMod = ((TFC_Core.getPercentGrown(this) * 0.5F) + 0.5F) * maxBaseHealth;
			this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(growthMod);
			setGrowingAge(-1);
		}

		if (!this.worldObj.isRemote && isPregnant() && TFC_Time.getTotalTicks() >= getTimeOfConception() + getPregnancyRequiredTime())
		{
			int i = rand.nextInt(3) + 1;
			for (int x = 0; x < i; x++)
			{
				ArrayList<Float> data = new ArrayList<Float>();
				data.add(mateSizeModCWTFC);
				EntityTransformSheepTFC baby = new EntityTransformSheepTFC(worldObj, this, data);
				baby.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
				baby.rotationYawHead = baby.rotationYaw;
				baby.renderYawOffset = baby.rotationYaw;
				worldObj.spawnEntityInWorld(baby);
				baby.setAge(TFC_Time.getTotalDays());
			}
			setPregnant(false);
		}

		/**
		 * This Cancels out the changes made to growingAge by EntityAgeable
		 * */
		TFC_Core.preventEntityDataUpdate = true;
		super.onLivingUpdate();
		TFC_Core.preventEntityDataUpdate = false;

		if (getHunger() > 144000 && rand.nextInt(100) == 0 && getHealth() < TFC_Core.getEntityMaxHealth(this) && !isDead)
			this.heal(1);
		else if (getHunger() < 144000 && super.isInLove())
			this.setInLove(false);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		mateSizeModCWTFC = nbt.getFloat("MateSizeCWTFC");
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setFloat("MateSizeCWTFC", mateSizeModCWTFC);
	}
}
