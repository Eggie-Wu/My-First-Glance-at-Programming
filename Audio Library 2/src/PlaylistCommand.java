import java.util.Optional;

public interface PlaylistCommand {
    Optional<Playable> execute();
    void undo();
}
