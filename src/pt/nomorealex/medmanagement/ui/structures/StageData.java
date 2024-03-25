package pt.nomorealex.medmanagement.ui.structures;

import javafx.stage.Modality;
import javafx.stage.Window;

public record StageData(double minWidth,
                        double minHeight,
                        Window windowOwner,
                        Modality modality,
                        String stageTitle,
                        String imageFilename
                        ) {}
