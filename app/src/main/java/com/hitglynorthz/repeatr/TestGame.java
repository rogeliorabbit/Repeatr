package com.hitglynorthz.repeatr;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class TestGame extends AppCompatActivity {
    Toolbar toolbar;

    RelativeLayout initGrid;
    TextView initTV, tv_round;

    GridLayout mainGrid;
    FloatingActionButton b_start;
    CardView cv1, cv2, cv3, cv4, cv5, cv6, cv7, cv8, cv9;

    List<Integer> allButtons = new ArrayList<>();
    List<Integer> tempButtons = new ArrayList<>();
    List<Integer> inputButtons = new ArrayList<>();

    private int loop;
    private int loopGame = 1;
    private long currentScore = 0;
    private long bestScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_game);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        initViews();
        initLists();
        initGame();

    }

    // initViews
    private void initViews() {
        // Grid
        initGrid = findViewById(R.id.initGrid);
        mainGrid = findViewById(R.id.mainGrid);
        initTV = findViewById(R.id.initTV);
        // FABs
        b_start = findViewById(R.id.b_start);
        // round
        tv_round = findViewById(R.id.tv_round);
        // CardViews
        cv1 = findViewById(R.id.cv1);
        cv1.setTag(1);
        cv2 = findViewById(R.id.cv2);
        cv2.setTag(2);
        cv3 = findViewById(R.id.cv3);
        cv3.setTag(3);
        cv4 = findViewById(R.id.cv4);
        cv4.setTag(4);
        cv5 = findViewById(R.id.cv5);
        cv5.setTag(5);
        cv5.setVisibility(View.GONE);
        cv6 = findViewById(R.id.cv6);
        cv6.setTag(6);
        cv7 = findViewById(R.id.cv7);
        cv7.setTag(7);
        cv8 = findViewById(R.id.cv8);
        cv8.setTag(8);
        cv9 = findViewById(R.id.cv9);
        cv9.setTag(9);
    }

    // initLists
    private void initLists() {
        allButtons.add(1);
        allButtons.add(2);
        allButtons.add(3);
        allButtons.add(4);
        //allButtons.add(5);
        allButtons.add(6);
        allButtons.add(7);
        allButtons.add(8);
        allButtons.add(9);
    }

    // initGame -> startGame
    private void initGame() {
        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b_start.setVisibility(View.GONE);

                getSupportActionBar().setTitle(R.string.score);
                getSupportActionBar().setSubtitle(R.string.round);

                new CountDownTimer(3100, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        initTV.setText("" + millisUntilFinished/1000);
                    }

                    @Override
                    public void onFinish() {
                        new CountDownTimer(1100, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                initTV.setText(R.string.go);
                            }

                            @Override
                            public void onFinish() {
                                initGrid.setVisibility(View.GONE);
                                initTV.setVisibility(View.GONE);
                                mainGrid.setVisibility(View.VISIBLE);

                                startGame();
                            }
                        }.start();
                    }
                }.start();
            }
        });
    }

    // startGame -> gameLogic
    private void startGame() {
        gameLogic();
    }

    // gameLogic -> generateButtons | clickableButtonsOFF
    private void gameLogic() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                inputButtons.clear();
                generateButtons();
                getSupportActionBar().setSubtitle(getResources().getString(R.string.round) + loopGame);
                tv_round.setText(Integer.toString(loopGame));
                clickableButtonsOFF();
            }
        }, 1000);
    }

    // generateButtons -> showButton | clickableButtonsON
    private void generateButtons() {
        //Collections.shuffle(allButtons);
        Random random = new Random();
        tempButtons.add(allButtons.get(random.nextInt(allButtons.size())));
        for(int i = 0; i < tempButtons.size(); i++) {
            Log.i("TAG", "list buttons: " + tempButtons.get(i));
        }

        final Handler handlerBshow = new Handler();
        final ListIterator<Integer> iterator = tempButtons.listIterator();
        handlerBshow.post(new Runnable(){
            @Override
            public void run() {
                Log.i("TAG", "button showed: " + tempButtons.get(loop));
                showButton(tempButtons.get(loop));
                loop++;
                if(loop < tempButtons.size()) {
                    handlerBshow.postDelayed(this, 1000);
                    Log.i("TAG", "in loop");
                    cv5.setVisibility(View.GONE);
                } else if(loop == tempButtons.size()){
                    Log.i("TAG", "end loop");
                    centerCheck();
                } else {
                    Log.i("TAG", "ERROR | size: " + tempButtons.get(loop) + " | i = " + loop);
                    loop = 0;
                }
            }
        });
    }

    // showButton | resetButtons
    private void showButton(int id) {
        switch(id) {
            case 1:
                cv1.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButtons();
                    }
                }, 500);
                break;
            case 2:
                cv2.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.pink));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButtons();
                    }
                }, 500);
                break;
            case 3:
                cv3.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButtons();
                    }
                }, 500);
                break;
            case 4:
                cv4.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButtons();
                    }
                }, 500);
                break;
            case 6:
                cv6.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButtons();
                    }
                }, 500);
                break;
            case 7:
                cv7.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.yellow));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButtons();
                    }
                }, 500);
                break;
            case 8:
                cv8.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.orange));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButtons();
                    }
                }, 500);
                break;
            case 9:
                cv9.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blue_gray));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resetButtons();
                    }
                }, 500);
                break;
        }
    }

    // resetButtons
    private void resetButtons() {
        Log.i("TAG", "reset");
        cv1.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        cv2.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        cv3.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        cv4.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        cv6.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        cv7.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        cv8.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        cv9.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
    }

    // clickableButtonsON -> addInputButtons | checkButtons
    private void clickableButtonsON() {
        cv1.setClickable(true);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputButtons(1);
                //cv1.setForeground(new ColorDrawable(ContextCompat.getColor(getApplicationContext(), R.color.red)));
                cv1.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cv1.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    }
                }, 500);
            }
        });

        cv2.setClickable(true);
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputButtons(2);
                cv2.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.pink));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cv2.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    }
                }, 500);
            }
        });

        cv3.setClickable(true);
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputButtons(3);
                cv3.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cv3.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    }
                }, 500);
            }
        });

        cv4.setClickable(true);
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputButtons(4);
                cv4.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cv4.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    }
                }, 500);
            }
        });

        cv6.setClickable(true);
        cv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputButtons(6);
                cv6.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cv6.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    }
                }, 500);
            }
        });

        cv7.setClickable(true);
        cv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputButtons(7);
                cv7.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.yellow));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cv7.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    }
                }, 500);
            }
        });

        cv8.setClickable(true);
        cv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputButtons(8);
                cv8.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.orange));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cv8.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    }
                }, 500);
            }
        });

        cv9.setClickable(true);
        cv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputButtons(9);
                cv9.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.blue_gray));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cv9.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    }
                }, 500);
            }
        });

        cv5.setClickable(true);
        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButtons();
            }
        });
    }

    // clickableButtonsOFF
    private void clickableButtonsOFF() {
        cv1.setClickable(false);
        cv2.setClickable(false);
        cv3.setClickable(false);
        cv4.setClickable(false);
        cv6.setClickable(false);
        cv7.setClickable(false);
        cv8.setClickable(false);
        cv9.setClickable(false);
    }

    // addInputButtons
    private void addInputButtons(int id) {
        inputButtons.add(id);
    }

    // checkButtons -> gameLogic OR finish |
    private void checkButtons() {
        if(tempButtons.equals(inputButtons)) {
            Log.i("TAG", "true");
            cv5.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    cv5.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.teal));
                }
            }, 500);
            updateScore();
            gameLogic();
        } else {
            Log.i("TAG", "false");
            cv5.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    cv5.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.teal));
                    /**/
                    gameOver();
                }
            }, 500);
        }
    }

    // updateScore
    private void updateScore() {
        if(loopGame <= 5) {
            currentScore += 5;
        } else if(loopGame > 5 && loopGame <= 10) {
            currentScore += 6;
        } else if(loopGame > 10 && loopGame <= 20) {
            currentScore += 8;
        } else if(loopGame > 20) {
            currentScore += 10;
        }
        getSupportActionBar().setTitle(getResources().getString(R.string.score) + currentScore);
    }

    // center Check
    private void centerCheck() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                cv5.setVisibility(View.VISIBLE);
                clickableButtonsON();
                loopGame++;
                loop = 0;
            }
        }, 1000);
    }

    // gameOver
    private void gameOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.game_over));
        if(currentScore == 0) {
            builder.setMessage(getResources().getString(R.string.git_gud));
        } else {
            builder.setMessage(getResources().getString(R.string.final_score) + currentScore);
        }
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = getIntent();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // ** test blinkButton **
    private void blinkButton(int id) {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.RESTART);
        cv5.startAnimation(anim);
    }

    //
    public static int getRandomId(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    //
    public static int getRandomButton(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
        //getRandomTime(new int[]{500, 2000, 3500, 5000})
    }

    //
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
