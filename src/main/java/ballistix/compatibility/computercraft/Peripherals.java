package ballistix.compatibility.computercraft;

import ballistix.common.inventory.container.ContainerMissileSilo;
import net.minecraftforge.common.capabilities.Capability;
import static dan200.computercraft.shared.Capabilities.CAPABILITY_PERIPHERAL;

// import com.mrh0.createaddition.blocks.electric_motor.ElectricMotorBlockEntity;

public class Peripherals {
    public static boolean isPeripheral(Capability<?> cap) {
        return cap == CAPABILITY_PERIPHERAL;
    }

    public static MissileSiloPeripheral createMissileSiloPeripheral(TileMissileSilo te) {
        return new MissileSiloPeripheral("missile_silo", te);
    }
}