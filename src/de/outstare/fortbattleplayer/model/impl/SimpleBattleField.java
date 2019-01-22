package de.outstare.fortbattleplayer.model.impl;

import java.awt.Point;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import de.outstare.fortbattleplayer.model.Area;
import de.outstare.fortbattleplayer.model.Battlefield;
import de.outstare.fortbattleplayer.model.Sector;

/**
 * A simple {@link Battlefield} which just is a set of {@link Area}s with
 * x/y-coordinates
 *
 * @author daniel
 */
public class SimpleBattleField implements Battlefield {

    private final Map<Point, Area> cells = new org.apache.commons.collections4.map.HashedMap<Point, Area>();

    private final Set<Sector> sectors = new java.util.LinkedHashSet<Sector>();

    private final int width;

    private final int height;

    /**
     *  @param width
     *  @param height
     *  @param cells
     *  @param sectors
     */
    public SimpleBattleField(final int width, final int height, final Collection<Area> cells, final Collection<Sector> sectors) {
        this.width = width;
        this.height = height;
        for (final Area cell : cells) {
            this.cells.put(cell.getLocation(), cell);
        }
        this.sectors.addAll(sectors);
    }

    /**
     *  generates Areas for the whole battlefield with as a single sector
     *
     *  @param width
     *  @param height
     */
    public SimpleBattleField(final int width, final int height) {
        this(width, height, Collections.<Area>emptyList(), Collections.<Sector>emptyList());
        final Sector theSector = new SimpleSector(0, false, false, 0, 0, false, 0, null);
        sectors.add(theSector);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final Area cell = new SimpleArea(x, y, theSector);
                cells.put(cell.getLocation(), cell);
            }
        }
    }

    /**
     *  @see de.outstare.fortbattleplayer.model.Battlefield#getHeight()
     */
    public int getHeight() {
        return height;
    }

    /**
     *  @see de.outstare.fortbattleplayer.model.Battlefield#getWidth()
     */
    public int getWidth() {
        return width;
    }

    /**
     *  @see de.outstare.fortbattleplayer.model.Battlefield#_getArea(int, int)
     */
    public Area _getArea(final int x, final int y) throws IllegalArgumentException {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            throw new IllegalArgumentException("the given coordinates does not exist on this battlefield!");
        }
        return cells.get(new Point(x, y));
    }
}
