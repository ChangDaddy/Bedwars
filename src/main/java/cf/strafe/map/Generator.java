package cf.strafe.map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;


@Getter
@RequiredArgsConstructor
public class Generator {
    private final GeneratorType generatorType;
    private final Location location;

    enum GeneratorType {
        BASE, DIAMOND, EMERALD
    }
}
