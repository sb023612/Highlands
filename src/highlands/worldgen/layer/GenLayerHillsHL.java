package highlands.worldgen.layer;

import java.util.ArrayList;
import java.util.Arrays;

import highlands.biome.BiomeGenBaseHighlands;
import highlands.HighlandsMain;
import net.minecraft.client.Minecraft;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.gen.structure.MapGenVillage;

public class GenLayerHillsHL extends GenLayer
{
	private boolean highlandsFlag;
	
    public GenLayerHillsHL(long par1, GenLayer par3GenLayer)
    {
        super(par1);
        this.parent = par3GenLayer;
        highlandsFlag = true;
    }

	/**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
    	
        int[] var5 = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
        int[] var6 = IntCache.getIntCache(par3 * par4);

        //System.out.println(HighlandsMain.listNullBiomes());
        
        for (int var7 = 0; var7 < par4; ++var7)
        {
            for (int var8 = 0; var8 < par3; ++var8)
            {
                this.initChunkSeed((long)(var8 + par1), (long)(var7 + par2));
                int var9 = var5[var8 + 1 + (var7 + 1) * (par3 + 2)];

                if (this.nextInt(3) == 0)
                {
                    int var10 = var9;

                    if (var9 == BiomeGenBase.desert.biomeID)
                    {
                        var10 = BiomeGenBase.desertHills.biomeID;
                    }
                    else if (var9 == BiomeGenBase.forest.biomeID)
                    {
                        var10 = BiomeGenBase.forestHills.biomeID;
                    }
                    else if (var9 == BiomeGenBase.taiga.biomeID)
                    {
                        var10 = BiomeGenBase.taigaHills.biomeID;
                    }
                    else if (var9 == BiomeGenBase.plains.biomeID)
                    {
                        var10 = BiomeGenBase.forest.biomeID;
                    }
                    else if (var9 == BiomeGenBase.icePlains.biomeID)
                    {
                        var10 = BiomeGenBase.iceMountains.biomeID;
                    }
                    else if (var9 == BiomeGenBase.jungle.biomeID)
                    {
                    	var10 = BiomeGenBase.jungleHills.biomeID;
                    }
                    
                    
                    
                    //sets sub-biome using the sub-biome lists of BiomeGenBaseHighlands
                    else if(BiomeGenBase.getBiomeGenArray()[var9] instanceof BiomeGenBaseHighlands){
                    	BiomeGenBaseHighlands biome = (BiomeGenBaseHighlands)BiomeGenBase.getBiomeGenArray()[var9];
                    	if(biome.subBiomes.size() > 0){
                    		var10 = biome.subBiomes.get(this.nextInt(biome.subBiomes.size())).biomeID;
                    		//System.out.println("  Sub biome parent: " + biome.biomeName + "  sub: " + BiomeGenBase.biomeList[var10].biomeName);
                    	}
                    	//else System.out.println("  " + biome.biomeName + " has no sub-biomes.");
                    }
                    
                    
                    
                    
                    if(var10 != var9){
                    	//System.out.println("Generating sub-biome: " + BiomeGenBase.biomeList[var10].biomeName);
                    }

                    if (var10 == var9)
                    {
                        var6[var8 + var7 * par3] = var9;
                    }
                    else
                    {
                        int var11 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
                        int var12 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
                        int var13 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
                        int var14 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

                        if (var11 == var9 && var12 == var9 && var13 == var9 && var14 == var9)
                        {
                            var6[var8 + var7 * par3] = var10;
                        }
                        else
                        {
                            var6[var8 + var7 * par3] = var9;
                        }
                    }
                }
                else
                {
                    var6[var8 + var7 * par3] = var9;
                }
            }
        }

        return var6;
    }
}
