package ballistix.compatibility.computercraft;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ballistix.common.inventory.container.ContainerMissileSilo;

import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

public class MissileSiloPeripheral implements IPeripheral {
    protected final List<IComputerAccess> connected = new ArrayList<>();
    protected String type;
    protected TileMissileSilo tileEntity;

    public MissileSiloPeripheral(String type, TileMissileSilo tileEntity) {
        this.type = type;
        this.tileEntity = tileEntity;
    }

    public List<IComputerAccess> getConnectedComputers() {
        return connected;
    }

    @Override
    public Object getTarget() {
        return tileEntity;
    }

    @Override
    public void attach(IComputerAccess computer) {
        connected.add(computer);
    }

    @Override
    public void detach(IComputerAccess computer) {
        connected.remove(computer);
    }

    @Override
    public boolean equals(IPeripheral iPeripheral) {
        return iPeripheral == this;
    }

    @LuaFunction
    public final String getType() {
        return type;
    }

    @LuaFunction(mainThread = true)
    public final void setTarget(int x, int y, int z) throws LuaException {
        if (tileEntity != null) {
            Location tgt = new Location(x, y, z);
            tileEntity.target = tgt;
        }
    }

    @LuaFunction(mainThread = true)
    public final String getTarget() throws LuaException {

        if (tileEntity != null) {
            int tgtX = tileEntity.target.intX;
            int tgtY = tileEntity.target.intY;
            int tgtZ = tileEntity.target.intZ;

            return "" + tgtX + " " + tgtY + " " + tgtZ;
        }
        return "Silo Not Found";
    }

    @LuaFunction(mainThread = true)
    public final void launch() throws LuaException {
        if (tileEntity != null) {
            tileEntity.launch();
        }
    }

    @LuaFunction(mainThread = true)
    public final float translate(int blocks, Optional<Integer> rpm) throws LuaException {
        if (tileEntity != null) {
            int _rpm = rpm.orElse(getSpeed());
            if (rpm.isPresent())
                setSpeed(blocks < 0 ? -_rpm : _rpm);
            return ElectricMotorBlockEntity.getDurationDistance(blocks, 0, _rpm) / 20f;
        }
        return 0f;
    }
}