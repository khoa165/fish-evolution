
public class Config {
  public static final String[] instructionEnglish =
      {"This game is designed for kids who are 10 years old or older.",
          "To play, simply use your mouse to drag the orange user's fish",
          "to eat only other fish that have less power than yours. You",
          "originally start with level 1 (in other words, that means your",
          "power is 1), as you fill up the experience bar, your level will",
          "increment. Remember, the small fish has a power of 0.5, while",
          "the orange white stripe fish has a power of 1.5 and the shark",
          "has a power of 2.5. The amount of XP points you gain from the",
          "fish is determined by the power of that fish.", "", "Good luck and have fun playing!!!",
          "Harry"};
  public static final String[] instructionVietnamese =
      {"Trò chơi này được thiết kế cho trẻ em 10 tuổi trở lên. Để chơi,",
          "đơn giản bạn chỉ cần di chuyển chuột để kéo chú cá màu cam",
          "để ăn những chú cá khác yếu hơn cá của bạn. Cá của bạn",
          "bắt đầu với cấp 1 (tức là, bạn đang có sức mạnh cấp độ 1, khi",
          "bạn đạt được điểm kinh nghiệm được yêu cầu, cấp độ cá của",
          "bạn sẽ tăng. Nhớ là, cá nhỏ có sức mạnh cấp độ 0.5, trong khi",
          "cá cam sọc trắng có sức mạnh cấp độ 1.5 và cá mập có sức",
          "mạnh cấp độ 2.5. Lượng kinh nghiệm bạn thu được từ những",
          "chú cá phụ thuộc vào cấp độ sức mạnh của chú cá đó.", "",
          "Chúc may mắn và chơi game vui vẻ!!!", "Harry"};

  public static String[] getInstruction(String language) {
    if (language.equals("Vietnamese")) {
      return instructionVietnamese;
    } else {
      return instructionEnglish;
    }
  }

  public static final String welcomeStatementEnglish =
      "WELCOME TO FISH EVOLUTION GAME!\nHARRY WISH YOU A GREAT TIME EXPLORING THIS "
          + "GAME!!!\nPLEASE GET STARTED BY CREATING A NEW PROFILE USING THE PROFILE BUTTON ON THE "
          + "BUTTON BAR!";
  public static final String welcomeStatementVietnamese = "CHÀO MỪNG BẠN ĐẾN VỚI TRÒ CHƠI CÁ LỚN ĂN"
      + " CÁ BÉ!\nHARRY CHÚC BẠN CÓ MỘT THỜI GIAN CHƠI GAME VUI VẺ!!!\nBẠN CÓ THỂ BẮT ĐẦU TẠO TÀI "
      + "KHOẢN BẰNG CÁCH NHẤN VÀO NÚT TÀI KHOẢN MỚI TRÊN THANH ĐIỀU KHIỂN!";

  public static String getWelcomeStatement(String language) {
    if (language.equals("Vietnamese")) {
      return welcomeStatementVietnamese;
    } else {
      return welcomeStatementEnglish;
    }
  }

  public static final String playerInEnglish = "Player";
  public static final String playerInVietnamese = "Người chơi";

  public static String getPlayerInLanguage(String language) {
    if (language.equals("Vietnamese")) {
      return playerInVietnamese;
    } else {
      return playerInEnglish;
    }
  }

  public static final String levelInEnglish = "Level";
  public static final String levelInVietnamese = "Cấp độ";

  public static String getLevelInLanguage(String language) {
    if (language.equals("Vietnamese")) {
      return levelInVietnamese;
    } else {
      return levelInEnglish;
    }
  }

  public static final String confirmNameEnglish =
      "YOU HAVE SUCCESSFULLY CREATED A NEW PROFILE WITH THE NAME OF: ";
  public static final String confirmNameVietnamese = "BẠN ĐÃ TẠO TÀI KHOẢN THÀNH CÔNG VỚI TÊN LÀ: ";

  public static String getConfirmName(String language) {
    if (language.equals("Vietnamese")) {
      return confirmNameVietnamese;
    } else {
      return confirmNameEnglish;
    }
  }

  public static final String warningModifyNameEnglish = "(DO NOT MODIFY THE NAME DIRECTLY AFTER "
      + "PRESSING ENTER BUTTON. PLEASE CLICK \"NEW PROFILE\" BUTTON TO ENTER A DIFFERENT NAME!)";
  public static final String warningModifyNameVietnamese = "VUI LÒNG KHÔNG CHỈNH SỬA TÊN TRỰC TIẾP "
      + "SAU KHI NHẤN NÚT ENTER. VUI LÒNG CHỌN TÀI KHOẢN MỚI ĐỂ NHẬP LẠI TÊN KHÁC!";

  public static String getWarningModifyName(String language) {
    if (language.equals("Vietnamese")) {
      return warningModifyNameVietnamese;
    } else {
      return warningModifyNameEnglish;
    }
  }

  public static final String readInstructionEnglish = "READ GAME INSTRUCTION";
  public static final String readInstructionVietnamese = "ĐỌC HƯỚNG DẪN CHƠI GAME";

  public static String getReadInstruction(String language) {
    if (language.equals("Vietnamese")) {
      return readInstructionVietnamese;
    } else {
      return readInstructionEnglish;
    }
  }

  public static final String startGameEnglish = "LET'S START A NEW GAME!";
  public static final String startGameVietnamese = "BẮT ĐẦU GAME MỚI!";

  public static String getStartGame(String language) {
    if (language.equals("Vietnamese")) {
      return startGameVietnamese;
    } else {
      return startGameEnglish;
    }
  }

  public static final String returnFromInstructionEnglish = "LET'S GO BACK TO PLAY THE FIRST GAME!";
  public static final String returnFromInstructionVietnamese = "HÃY TRỞ LẠI ĐỂ BẮT ĐẦU TRÒ CHƠI!";

  public static String getReturnFromInstruction(String language) {
    if (language.equals("Vietnamese")) {
      return returnFromInstructionVietnamese;
    } else {
      return returnFromInstructionEnglish;
    }
  }

  public static final String newProfileEnglish = "NEW PROFILE";
  public static final String newProfileVietnamese = "TÀI KHOẢN MỚI";

  public static String getNewProfile(String language) {
    if (language.equals("Vietnamese")) {
      return newProfileVietnamese;
    } else {
      return newProfileEnglish;
    }
  }

  public static final String nameRequestEnglish = "PLEASE ENTER YOUR NAME IN THE BOX BELOW!";
  public static final String nameRequestVietnamese = "VUI LÒNG NHẬP TÊN BẠN VÀO Ô TRỐNG BÊN DƯỚI!";

  public static String getNameRequest(String language) {
    if (language.equals("Vietnamese")) {
      return nameRequestVietnamese;
    } else {
      return nameRequestEnglish;
    }
  }

  public static final String pauseEnglish = "PAUSE GAME";
  public static final String pauseVietnamese = "DỪNG TRÒ CHƠI";

  public static String getPause(String language) {
    if (language.equals("Vietnamese")) {
      return pauseVietnamese;
    } else {
      return pauseEnglish;
    }
  }

  public static final String pauseWarningEnglish = "YOU HAVE PAUSED THE GAME!\nYOU CAN EITHER "
      + "START A NEW GAME, CONTINUE YOUR CURRENT GAME, OR LOG OUT AS YOU WISH! ";
  public static final String pauseWarningVietnamese = "BẠN ĐÃ DỪNG TRÒ CHƠI!\nBẠN CÓ THỂ BẮT ĐẦU "
      + "GAME MỚI, HOẶC TIẾP TỤC VỚI LƯỢT CHƠI HIỆN TẠI, HOẶC ĐĂNG XUẤT TÀI KHOẢN NẾU BẠN MUỐN!";

  public static String getPauseWarning(String language) {
    if (language.equals("Vietnamese")) {
      return pauseWarningVietnamese;
    } else {
      return pauseWarningEnglish;
    }
  }

  public static final String restartInEnglish = "RESTART";
  public static final String restartInVietnamese = "CHƠI LẠI";

  public static String getRestartInLanguage(String language) {
    if (language.equals("Vietnamese")) {
      return restartInVietnamese;
    } else {
      return restartInEnglish;
    }
  }

  public static final String resumeInEnglish = "RESUME";
  public static final String resumeInVietnamese = "TIẾP TỤC";

  public static String getResumeInLanguage(String language) {
    if (language.equals("Vietnamese")) {
      return resumeInVietnamese;
    } else {
      return resumeInEnglish;
    }
  }

  public static final String logoutInEnglish = "LOG OUT";
  public static final String logoutInVietnamese = "ĐĂNG XUẤT";

  public static String getLogoutInLanguage(String language) {
    if (language.equals("Vietnamese")) {
      return logoutInVietnamese;
    } else {
      return logoutInEnglish;
    }
  }

  public static final String settingEnglish = "SETTINGS";
  public static final String settingVietnamese = "CÀI ĐẶT";

  public static String getSetting(String language) {
    if (language.equals("Vietnamese")) {
      return settingVietnamese;
    } else {
      return settingEnglish;
    }
  }

  public static final String presentOptionEnglish = "CHOOSE ONE OF THE FOLLOWING OPTIONS!";
  public static final String presentOptionVietnamese = "CHỌN MỘT TRONG NHỮNG CÀI ĐẶT SAU!";

  public static String getPresentOption(String language) {
    if (language.equals("Vietnamese")) {
      return presentOptionVietnamese;
    } else {
      return presentOptionEnglish;
    }
  }

  public static final String difficultyLevelEnglish = "DIFFICULTY LEVEL";
  public static final String difficultyLevelVietnamese = "ĐỘ KHÓ";

  public static String getDifficultyLevel(String language) {
    if (language.equals("Vietnamese")) {
      return difficultyLevelVietnamese;
    } else {
      return difficultyLevelEnglish;
    }
  }

  public static final String languageEnglish = "ENGLISH";
  public static final String languageVietnamese = "TIẾNG VIỆT";

  public static String getLanguage(String language) {
    if (language.equals("Vietnamese")) {
      return languageVietnamese;
    } else {
      return languageEnglish;
    }
  }

  public static final String performanceEnglish = "YOUR PERFORMANCE";
  public static final String performanceVietnamese = "THÀNH TÍCH CỦA BẠN";

  public static String getPerformance(String language) {
    if (language.equals("Vietnamese")) {
      return performanceVietnamese;
    } else {
      return performanceEnglish;
    }
  }

  public static final String rankingsEnglish = "RANKINGS";
  public static final String rankingsVietnamese = "BẢNG XẾP HẠNG";

  public static String getRankings(String language) {
    if (language.equals("Vietnamese")) {
      return rankingsVietnamese;
    } else {
      return rankingsEnglish;
    }
  }

  public static final String soundOnEnglish = "SOUND: ON";
  public static final String soundOnVietnamese = "ÂM THANH: BẬT";

  public static String getSoundOn(String language) {
    if (language.equals("Vietnamese")) {
      return soundOnVietnamese;
    } else {
      return soundOnEnglish;
    }
  }

  public static final String soundOffEnglish = "SOUND: OFF";
  public static final String soundOffVietnamese = "ÂM THANH: TẮT";

  public static String getSoundOff(String language) {
    if (language.equals("Vietnamese")) {
      return soundOffVietnamese;
    } else {
      return soundOffEnglish;
    }
  }

  public static final String historyEnglish = "GAME HISTORY";
  public static final String historyVietnamese = "LỊCH SỬ GAME";

  public static String getHistory(String language) {
    if (language.equals("Vietnamese")) {
      return historyVietnamese;
    } else {
      return historyEnglish;
    }
  }

  public static final String newGameEnglish = "START NEW GAME";
  public static final String newGameVietnamese = "BẮT ĐẦU GAME MỚI";

  public static String getNewGame(String language) {
    if (language.equals("Vietnamese")) {
      return newGameVietnamese;
    } else {
      return newGameEnglish;
    }
  }

  public static final String confirmNewGameEnglish = "ARE YOU SURE YOU WANT TO START A NEW GAME?";
  public static final String confirmNewGameVietnamese = "BẠN CÓ CHẮC LÀ MUỐN BẮT ĐẦU GAME MỚI?";

  public static String getConfirmNewGame(String language) {
    if (language.equals("Vietnamese")) {
      return confirmNewGameVietnamese;
    } else {
      return confirmNewGameEnglish;
    }
  }

  public static final String positiveConfirmEnglish = "YES, I WOULD LIKE TO START A NEW GAME!";
  public static final String positiveConfirmVietnamese = "CÓ, TÔI MUỐN BẮT ĐẦU GAME MỚI!";

  public static String getPositiveConfirm(String language) {
    if (language.equals("Vietnamese")) {
      return positiveConfirmVietnamese;
    } else {
      return positiveConfirmEnglish;
    }
  }

  public static final String negativeConfirm1English = "NO, I WANT TO LOG OUT!";
  public static final String negativeConfirm1Vietnamese = "KHÔNG, TÔI MUỐN ĐĂNG XUẤT!";

  public static String getNegativeConfirm1(String language) {
    if (language.equals("Vietnamese")) {
      return negativeConfirm1Vietnamese;
    } else {
      return negativeConfirm1English;
    }
  }

  public static final String negativeConfirm2English =
      "NO, I WANT TO CONTINUE WITH MY CURRENT GAME!";
  public static final String negativeConfirm2Vietnamese =
      "KHÔNG, TÔI MUỐN TIẾP TỤC VỚI GAME HIỆN TẠI!";

  public static String getNegativeConfirm2(String language) {
    if (language.equals("Vietnamese")) {
      return negativeConfirm2Vietnamese;
    } else {
      return negativeConfirm2English;
    }
  }


}
