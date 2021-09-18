package cf.strafe.map;

import cf.strafe.util.RandomUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


@Getter
@RequiredArgsConstructor
public class Generator {
    private final GeneratorType generatorType;
    private final Location location;

    public enum GeneratorType {
        BASE, DIAMOND, EMERALD
    }

    public void generate() {
        /*
        This can be improved...
         */

        switch (generatorType) {
            case BASE: {
                int random = RandomUtil.randomNumber(2, 0);
                switch (random) {
                    case 0:
                    case 1: {
                        ItemStack itemStack = new ItemStack(Material.IRON_INGOT);
                        location.getWorld().dropItemNaturally(location, itemStack);
                        break;
                    }
                    case 2: {
                        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
                        location.getWorld().dropItemNaturally(location, itemStack);
                        break;
                    }
                }
                break;
            }
            case DIAMOND: {
                ItemStack itemStack = new ItemStack(Material.DIAMOND);
                location.getWorld().dropItemNaturally(location, itemStack);
                break;
            }
            case EMERALD: {
                ItemStack itemStack = new ItemStack(Material.EMERALD);
                location.getWorld().dropItemNaturally(location, itemStack);
                break;
            }
        }

    }
}
