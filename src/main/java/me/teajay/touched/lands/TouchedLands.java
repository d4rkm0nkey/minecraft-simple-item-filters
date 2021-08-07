package me.teajay.touched.lands;

import me.teajay.touched.lands.structures.TimberHouse.TimberHouseFeature;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class TouchedLands implements ModInitializer {
	public static final String MODID = "touched-lands";
	public static final StructureFeature<DefaultFeatureConfig> TIMBER_STRUCTURE = new TimberHouseFeature(DefaultFeatureConfig.CODEC);
	public static final ConfiguredStructureFeature<?, ?> TIMBER_STRUCTURE_CONFIGURED = TIMBER_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
	public static final Identifier TIMBER_STRUCTURE_ID = new Identifier(MODID, "timber-house");
	@Override
	public void onInitialize() {
		FabricStructureBuilder.create(TIMBER_STRUCTURE_ID, TIMBER_STRUCTURE)
				.step(GenerationStep.Feature.SURFACE_STRUCTURES)
				.defaultConfig(32 , 8 , 12345)
				.adjustsSurface()
				.register();

		RegistryKey<ConfiguredStructureFeature<?, ?>> timberStructureConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY,
				TIMBER_STRUCTURE_ID);
		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, timberStructureConfigured.getValue(), TIMBER_STRUCTURE_CONFIGURED);

		BiomeModifications.addStructure(BiomeSelectors.all(), timberStructureConfigured);
	}
}