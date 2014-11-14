import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;


public class Music extends JFrame{

	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	JSpinner spinner = new JSpinner();
	JLabel chordLabel = new JLabel("Chords");
	SpinnerNumberModel model = new SpinnerNumberModel(3,1,10,1);
	Random generator = new Random();
	Timer timer = null;
	Boolean cycleBool = false;
	int i;
	JButton fifthsButton = new JButton("Fifths");
	int chordChoice;
	String chordtoDisplay;
	String[] chords = {"A", "B" ,"C", "D", "E", "F", "G", "A#", "B#", "C#", "D#", "E#", "F#", "G#", "Ab", "Bb", "Cb", "Db", "Eb", "Fb", "Gb"};
	String[] fifths = {"C", "F", "Bb", "Eb", "Ab", "Db", "Gb", "B", "E", "A", "D", "G"};

	public Music()
	{
		setTitle("Music Chords");

		getContentPane().setLayout(new GridBagLayout());

		GridBagConstraints gridC = new GridBagConstraints();

		spinner.setModel(model);

		gridC.gridx = 0;
		gridC.gridy = 0;
		getContentPane().add(spinner, gridC);

		gridC.gridx = 0;
		gridC.gridy = 1;
		getContentPane().add(start, gridC);

		gridC.gridx = 0;
		gridC.gridy = 2;
		getContentPane().add(stop, gridC);

		gridC.gridx = 0;
		gridC.gridy = 3;
		getContentPane().add(fifthsButton, gridC);

		gridC.gridx = 0;
		gridC.gridy = 4;
		getContentPane().add(chordLabel, gridC);

		chordLabel.setFont(new Font("Arial", Font.PLAIN, 28));

		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				startButtonActionPerformed(e);
			}
		});

		stop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				stopButtonActionPerformed(e);
			}
		});

		fifthsButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				fifthsButtonActionPerformed(e);
			}
		});

		stop.setEnabled(false);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

		pack();
	}

	protected void fifthsButtonActionPerformed(ActionEvent e) 
	{
		start.setEnabled(false);
		cycleBool = true;
		//fifthsButton.setEnabled(false);
		stop.setEnabled(true);
		int interval = Integer.parseInt(spinner.getValue().toString());
		interval *= 1000;
		timer = new Timer(interval, new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				timerTick(e);
			}
		});

		timer.start();
		chordLabel.setText(chords[i]);
	}

	protected void stopButtonActionPerformed(ActionEvent e) 
	{
		timer.stop();
		start.setEnabled(true);
		stop.setEnabled(false);
	}

	protected void startButtonActionPerformed(ActionEvent e) 
	{
		start.setEnabled(false);
		stop.setEnabled(true);
		int interval = Integer.parseInt(spinner.getValue().toString());
		interval *= 1000;
		timer = new Timer(interval, new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				timerTick(e);
			}
		});

		timer.start();
		chordChoice = generator.nextInt(chords.length + 1);
		chordtoDisplay = chords[chordChoice];
		chordLabel.setText(chordtoDisplay);
	}

	protected void timerTick(ActionEvent e) 
	{
		if (cycleBool)
		{
			chordChoice = generator.nextInt(chords.length);
			chordtoDisplay = chords[chordChoice];
			chordLabel.setText(chordtoDisplay);
		}
		else
		{
			i++;
			chordLabel.setText(chords[i - 1]);
			if (i == fifths.length)
			{
				cycleBool = false;
				System.out.println("DONE WITH THE CYCLE!!!");
			}
		}
	}

	public static void main(String[] args) 
	{
		Music music = new Music();
		music.setVisible(true);
	}

}
