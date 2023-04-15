package views.pieces;

import views.Playfield;
import views.models.Point;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class PieceFactoryImpl implements PieceFactory<Piece> {
    // all the registered piece types
    private List<Class> piecesTypes = new ArrayList<>();
    // The pieces has different geometries, this collection is a know pivot as reference.
    private Map<Class, Point> preferredPivots = new HashMap<>();

    public PieceFactoryImpl() {
        // Register all the classes, one sofisticated way is to create an annotation and retrieve all the java classes with that annotation,
        // we are doing manually for simplicity, another one is to scan all the classes that extends the pieces.Piece base class
        piecesTypes.add(ITetromino.class);
        piecesTypes.add(ZTetromino.class);
        // ToDo: add the other pieces we will support and update the preferred pivots collection for any new class added
        // computing the pivot can be done automatically based on playfield properties and the blocks' properties from the piece
        preferredPivots.put(ITetromino.class, new Point(-1, Playfield.COLUMNS /2-2));
        preferredPivots.put(ZTetromino.class, new Point(-2, Playfield.COLUMNS /2-2));
    }

    @Override
    public Optional<Piece> createPiece() {
        Random rand = new Random();
        int randIndex = rand.nextInt(piecesTypes.size());
        Class<? extends Piece> clazz = piecesTypes.get(randIndex);
        Point pivot = preferredPivots.get(clazz);

        try {
            Method createMethod = clazz.getMethod("create", Point.class);
            return Optional.ofNullable((Piece)createMethod.invoke(null, pivot.clone()));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
