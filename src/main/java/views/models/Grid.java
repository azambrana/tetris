package views.models;

import views.pieces.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Grid {
    private int rowsCounts;
    private List<Block> blocks;
    private int columnsCount;

    public Grid(int rowsCounts, int columnsCount) {
        blocks = new ArrayList<Block>();
        this.rowsCounts = rowsCounts;
        this.columnsCount = columnsCount;
    }

    public void addBlocks(List<Block> blocks) {
        this.blocks.addAll(blocks);
    }

    public void removeRow(int row) {
        for (Block block: blocks) {
            if ( block.getPosition().getRow() == row ) {
                blocks.remove(block);
            }
        }
    }

    public List<Block> getFilledRows() {
        List<Block> result = new ArrayList<Block>();
        for (int row = 0; row < rowsCounts; row++) {
            List<Block> blocksOnRow = getBlocksByRow(row);
            if (blocksOnRow.size() == columnsCount) {
                result.addAll(blocksOnRow);
            }
        }

        return result;
    }

    private List<Block> getBlocksByRow(int row) {
        List<Block> result = new ArrayList<>();
        for (Block block: blocks) {
            if ( block.getPosition().getRow() == row ) {
                result.add(block);
            }
        }
        return result;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public List<Point> getBoundaries() {
        return getBlocks().stream().map(block -> new Point(block.getRow(), block.getColumn())).collect(Collectors.toList());
    }
}
