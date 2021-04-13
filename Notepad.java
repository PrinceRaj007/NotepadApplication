import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

class Notepad extends WindowAdapter implements ActionListener {
	Frame f, ff, fr, nf, fnew, frc;
	MenuBar mb;
	Menu m1, m2;
	MenuItem n, o, s, sa, e, fin, finr;
	TextArea t = new TextArea();
	TextField tf = new TextField(), tf1, tf2;
	Pattern p;
	Matcher m;
	Label l3;
	Button bf, cf, fn1, r1, ra1, c1, c2, bd, bs, bc, b5;
	String name = null, path = null, str, patt, rep, text;
	int flag = 0, start = 0, end = 0, nl = 0, count = 0, flag1 = 0, flag2 = 0, i, flag0 = 0;
	int index = 0, start1 = -1, end1 = -1, close = 0;

	public Notepad() throws IOException {
		f = new Frame();
		f.setSize(800, 500);
		f.setLocation(100, 100);
		f.setTitle("Untitled");
		mb = new MenuBar();
		m1 = new Menu("File");
		m2 = new Menu("Edit");

		n = new MenuItem("New");
		o = new MenuItem("Open");
		s = new MenuItem("Save");
		sa = new MenuItem("Save As");
		e = new MenuItem("Exit");
		fin = new MenuItem("Find");
		finr = new MenuItem("Find Replace");

		n.addActionListener(this);
		o.addActionListener(this);
		s.addActionListener(this);
		sa.addActionListener(this);
		e.addActionListener(this);
		fin.addActionListener(this);
		finr.addActionListener(this);
		f.addWindowListener(this);

		f.setMenuBar(mb);
		mb.add(m1);
		mb.add(m2);
		m1.add(n);
		m1.add(o);
		m1.add(s);
		m1.add(sa);
		m1.add(e);
		m2.add(fin);
		m2.add(finr);
		f.setVisible(true);

		f.add(t);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getActionCommand() == "New") {
				if (name != null && isSaved())
					clear();
				else if ((name != null) && !((t.getText()).equals(""))) {
					fnew = new Frame();
					fnew.setLayout(new GridBagLayout());
					fnew.setSize(300, 150);
					fnew.setVisible(true);
					fnew.setLocation(300, 300);

					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.gridwidth = 3;
					gbc.gridheight = 1;
					// gbc.anchor=GridBagConstraints.CENTER;
					Label ln = new Label("Do you want to save changes ??");
					fnew.add(ln, gbc);

					gbc.gridx = 0;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					gbc.ipadx = 5;
					gbc.ipady = 10;
					bs = new Button("Save");
					bs.addActionListener(e13 -> {
						try {
							save();
						} catch (IOException ee) {
						}
						exit(fnew);
					});
					fnew.add(bs, gbc);

					gbc.gridx = 1;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					Insets iq = new Insets(10, 5, 5, 5);
					gbc.insets = iq;
					bd = new Button("Don't Save");
					bd.addActionListener(e12 -> {
						clear();
						exit(fnew);
					});
					fnew.add(bd, gbc);

					gbc.gridx = 2;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					bc = new Button("Cancel");
					bc.addActionListener(e11 -> exit(fnew));
					fnew.addWindowListener(this);
					fnew.add(bc, gbc);

					// System.out.println("Prince");
					// save();
				} else if (!((t.getText()).equals("")) && (name == null || name.equals(""))) {
					fnew = new Frame();
					fnew.setLayout(new GridBagLayout());
					fnew.setSize(300, 150);
					fnew.setVisible(true);
					fnew.setLocation(300, 300);

					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.gridwidth = 3;
					gbc.gridheight = 1;
					// gbc.anchor=GridBagConstraints.CENTER;
					Label ln = new Label("Do you want to save changes ??");
					fnew.add(ln, gbc);

					gbc.gridx = 0;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					gbc.ipadx = 5;
					gbc.ipady = 10;
					bs = new Button("Save");
					bs.addActionListener(e13 -> {
						try {
							saveAs();
						} catch (IOException ee) {
						}
						exit(fnew);
					});
					fnew.add(bs, gbc);

					gbc.gridx = 1;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					Insets iq = new Insets(10, 5, 5, 5);
					gbc.insets = iq;
					bd = new Button("Don't Save");
					bd.addActionListener(e12 -> {
						clear();
						exit(fnew);
					});
					fnew.add(bd, gbc);

					gbc.gridx = 2;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					bc = new Button("Cancel");
					bc.addActionListener(e11 -> exit(fnew));
					fnew.addWindowListener(this);
					fnew.add(bc, gbc);

				}
			} else if (e.getActionCommand() == "Open") {
				String str = t.getText();
				if (str.equals("") || isSaved())
					open();
				else if (name != null) {
					fnew = new Frame();
					fnew.setLayout(new GridBagLayout());
					fnew.setSize(300, 150);
					fnew.setVisible(true);
					fnew.setLocation(300, 300);

					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.gridwidth = 3;
					gbc.gridheight = 1;
					// gbc.anchor=GridBagConstraints.CENTER;
					Label ln = new Label("Do you want to save changes ??");
					fnew.add(ln, gbc);

					gbc.gridx = 0;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					gbc.ipadx = 5;
					gbc.ipady = 10;
					bs = new Button("Save");
					bs.addActionListener(e13 -> {
						try {
							save();
						} catch (IOException ee) {
						}
						exit(fnew);
					});
					fnew.add(bs, gbc);

					gbc.gridx = 1;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					Insets iq = new Insets(10, 5, 5, 5);
					gbc.insets = iq;
					bd = new Button("Don't Save");
					bd.addActionListener(e12 -> {
						try {
							open();
						} catch (IOException ed) {
						}
						exit(fnew);
					});
					fnew.add(bd, gbc);

					gbc.gridx = 2;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					bc = new Button("Cancel");
					bc.addActionListener(e11 -> exit(fnew));
					fnew.addWindowListener(this);
					fnew.add(bc, gbc);

				} else if (name == null || name.equals("")) {
					fnew = new Frame();
					fnew.setLayout(new GridBagLayout());
					fnew.setSize(300, 150);
					fnew.setVisible(true);
					fnew.setLocation(300, 300);

					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = 0;
					gbc.gridwidth = 3;
					gbc.gridheight = 1;
					// gbc.anchor=GridBagConstraints.CENTER;
					Label ln = new Label("Do you want to save changes ??");
					fnew.add(ln, gbc);

					gbc.gridx = 0;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					gbc.ipadx = 5;
					gbc.ipady = 10;
					bs = new Button("Save");
					bs.addActionListener(e13 -> {
						try {
							saveAs();
						} catch (IOException ee) {
						}
						exit(fnew);
					});
					fnew.add(bs, gbc);

					gbc.gridx = 1;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					Insets iq = new Insets(10, 5, 5, 5);
					gbc.insets = iq;
					bd = new Button("Don't Save");
					bd.addActionListener(e12 -> {
						try {
							open();
						} catch (IOException ert) {
						}
						exit(fnew);
					});
					fnew.add(bd, gbc);

					gbc.gridx = 2;
					gbc.gridy = 1;
					gbc.gridwidth = 1;
					gbc.gridheight = 1;
					bc = new Button("Cancel");
					bc.addActionListener(e11 -> exit(fnew));
					fnew.addWindowListener(this);
					fnew.add(bc, gbc);

				} else
					open();
			} else if (e.getActionCommand() == "Save") {
				if ((name == null || name.equals("")) && !((t.getText()).equals(""))) {
					// System.out.println("Prince");
					FileDialog fd = new FileDialog(f, "Save", FileDialog.SAVE);
					fd.setVisible(true);
					name = fd.getFile();
					path = fd.getDirectory();
					if (name != null && path != null) {
						File fn = new File(path, name);
						fn.createNewFile();// duplicate file
						BufferedWriter fo = new BufferedWriter(new FileWriter(fn));
						fo.write(t.getText());
						fo.close();
					}

				} else if (name != null && !name.equals("")) {
					System.out.println("PrinceRaj");
					save();
				}
				if (e.getSource() == bs)
					exit(fnew);//////////////////////////////////////////////////////////////////////////////////////////////////////////
			} else if (e.getActionCommand() == "Save As") {
				if (!((t.getText()).equals(""))) {
					FileDialog fd = new FileDialog(f, "Save As", FileDialog.SAVE);
					fd.setVisible(true);
					name = fd.getFile();
					path = fd.getDirectory();
					if (name != null && path != null) {
						String st = "";
						File fn = new File(path, name);
						fn.createNewFile();// duplicate file
						BufferedWriter fo = new BufferedWriter(new FileWriter(fn));
						fo.write(t.getText());
						fo.close();
						FileInputStream fis = new FileInputStream(fn);
						int ch;
						while ((ch = fis.read()) != -1)
							st += (char) ch;
						t.setText(st);
						f.setTitle(name);
					}
				}

			} else if (e.getActionCommand() == "Exit") {
				exit(f);
			} else if (e.getActionCommand() == "Find") {
				ff = new Frame();
				ff.setTitle("Find");
				ff.setSize(400, 200);
				ff.setLocation(300, 300);
				ff.setLayout(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridx = gbc.gridy = 0;
				gbc.gridwidth = gbc.gridheight = 1;
				gbc.ipadx = gbc.ipady = 5;
				Label l1 = new Label("Find What");
				ff.add(l1, gbc);

				tf = new TextField(20);
				gbc.gridx = 1;
				gbc.gridy = 0;
				ff.add(tf, gbc);

				bf = new Button("Find Next");
				gbc.gridx = 1;
				gbc.gridy = 1;
				// gbc.ipadx=gbc.ipady=10;
				Insets i = new Insets(5, 5, 5, 5);
				gbc.insets = i;
				ff.add(bf, gbc);

				cf = new Button("Close");
				cf.addActionListener((e1) -> {
					exit(ff);
				});
				bf.addActionListener(e2 -> find());

				gbc.gridx = 2;
				gbc.gridy = 1;
				ff.add(cf, gbc);

				ff.addWindowListener(this);
				ff.setVisible(true);
			} else if (e.getSource() == finr) {
				fr = new Frame();
				fr.setTitle("Find");
				fr.setSize(420, 230);
				fr.setLocation(300, 300);
				fr.setLayout(new GridBagLayout());
				GridBagConstraints gbc = new GridBagConstraints();
				gbc.gridx = gbc.gridy = 0;
				gbc.gridwidth = gbc.gridheight = 1;
				gbc.ipadx = gbc.ipady = 5;
				Insets i = new Insets(5, 5, 5, 5);
				gbc.insets = i;
				Label l1 = new Label("Find ");
				fr.add(l1, gbc);

				tf1 = new TextField(20);
				gbc.gridx = 1;
				gbc.gridy = 0;
				fr.add(tf1, gbc);

				Label l2 = new Label("Replace With");
				gbc.gridx = 0;
				gbc.gridy = 1;
				fr.add(l2, gbc);

				tf2 = new TextField(20);
				gbc.gridx = 1;
				gbc.gridy = 1;
				fr.add(tf2, gbc);

				fn1 = new Button("Find Next");
				gbc.gridx = 0;
				gbc.gridy = 2;
				fr.add(fn1, gbc);
				fn1.addActionListener(e4 -> find1());

				r1 = new Button("Replace");
				gbc.gridx = 1;
				gbc.gridy = 2;
				fr.add(r1, gbc);
				r1.addActionListener(e5 -> replace());

				ra1 = new Button("Replace All");
				gbc.gridx = 0;
				gbc.gridy = 3;
				Insets ii = new Insets(10, 5, 20, 5);
				gbc.insets = ii;
				fr.add(ra1, gbc);
				ra1.addActionListener(e6 -> replaceall());

				c1 = new Button("Close");
				gbc.gridx = 1;
				gbc.gridy = 3;
				gbc.ipadx = 20;
				ii = new Insets(10, 5, 20, 5);
				gbc.insets = ii;
				fr.add(c1, gbc);
				c1.addActionListener((e3) -> {
					exit(fr);
				});

				fr.addWindowListener(this);
				fr.setVisible(true);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	private void save() throws IOException {
		if (name != null && path != null) {
			File fn = new File(path, name);
			BufferedWriter fo = new BufferedWriter(new FileWriter(fn));
			fo.write(t.getText());
			fo.close();
		}

	}

	private void open() throws IOException {
		String st = "";
		FileDialog fd = new FileDialog(f, "Open", FileDialog.LOAD);
		fd.setVisible(true);
		String name1 = fd.getFile();
		String path1 = fd.getDirectory();
		if (path1 != null && name1 != null) {
			path = path1;
			name = name1;
			File fn = new File(path, name);
			if (!fn.exists())
				notExist();
			else {
				FileInputStream fis = new FileInputStream(fn);
				int ch;
				while ((ch = fis.read()) != -1)
					st += (char) ch;
				t.setText(st);
				f.setTitle(name);
			}

		}

	}

	public static void main(String args[]) throws Exception {
		Notepad note = new Notepad();
	}

	public void windowClosing(WindowEvent e) {
		boolean bool = false;
		flag1 = count = 0;
		start1 = end1 = index = -1;

		try {
			bool = isSaved();
		} catch (Exception ew) {
		}
		// System.out.println(bool);
		// System.out.println((!((t.getText()).equals("")) && !bool));
		// System.out.println((name==null && !((t.getText()).equals(""))));
		if (e.getSource() == f
				&& ((!((t.getText()).equals("")) && !bool) || (name == null && !((t.getText()).equals(""))))) {
			fnew = new Frame();
			fnew.setLayout(new GridBagLayout());
			fnew.setSize(300, 150);
			fnew.setVisible(true);
			fnew.setLocation(300, 300);

			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 3;
			gbc.gridheight = 1;
			// gbc.anchor=GridBagConstraints.CENTER;
			Label ln = new Label("Do you want to save changes ??");
			fnew.add(ln, gbc);

			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.ipadx = 5;
			gbc.ipady = 10;
			bs = new Button("Save");
			bs.addActionListener(this);
			fnew.add(bs, gbc);

			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			Insets iq = new Insets(10, 5, 5, 5);
			gbc.insets = iq;
			bd = new Button("Don't Save");
			bd.addActionListener(e12 -> {
				exit(fnew);
				f.setVisible(false);
				f.dispose();
			});
			fnew.add(bd, gbc);

			gbc.gridx = 2;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			bc = new Button("Cancel");
			bc.addActionListener(e11 -> exit(fnew));
			fnew.addWindowListener(this);
			fnew.add(bc, gbc);
		} else {
			Window w = e.getWindow();
			w.setVisible(false);
			w.dispose();
			if (e.getSource() == f)
				System.exit(1);
		}

	}

	void exit(Frame f) {
		start = end = 0;
		flag1 = flag0 = count = 0;
		start1 = end1 = index = -1;
		f.setVisible(false);
		f.dispose();
		if (f == this.f)
			System.exit(1);
	}

	void find() {
		System.out.println("Find clicked");
		if (flag1 == 0) {
			str = t.getText();
			patt = tf.getText();
			// patt=patt+"(?![A-Za-z]+;|#[0-9]+;)";
			p = Pattern.compile(patt);
			m = p.matcher(str);
			flag1 = 1;
		}
		nl = 0;
		if (m.find()) {
			end = m.end();
			start = m.start();
			for (i = 0; i < end; i++)
				if (str.charAt(i) == '\n')
					nl++;
			System.out.println("nl=" + nl);
			t.requestFocus();
			t.select(start - nl, end - nl);
		} else {
			nf = new Frame();
			nf.setLayout(new FlowLayout());
			nf.setSize(150, 100);
			nf.setLocation(400, 400);
			nf.setVisible(true);
			nf.add(new Label("No match Found"));
			c2 = new Button("close");
			c2.addActionListener(e5 -> exit(nf));
			nf.add(c2);
			nf.addWindowListener(this);
			flag0 = 1;
		}
	}

	void find1() {
		System.out.println("Find clicked");
		if (flag1 == 0) {
			str = t.getText();
			patt = tf1.getText();
			// patt=patt+"(?![A-Za-z]+;|#[0-9]+;)";
			p = Pattern.compile(patt);
			m = p.matcher(str);
			flag1 = 1;
		}
		nl = 0;
		if (m.find()) {
			end = m.end();
			start = m.start();
			for (i = 0; i < end; i++)
				if (str.charAt(i) == '\n')
					nl++;
			System.out.println("nl=" + nl);
			t.requestFocus();
			t.select(start - nl, end - nl);
		} else {
			nf = new Frame();
			nf.setLayout(new FlowLayout());
			nf.setSize(150, 100);
			nf.setLocation(400, 400);
			nf.setVisible(true);
			nf.add(new Label("No match Found"));
			c2 = new Button("close");
			c2.addActionListener(e5 -> exit(nf));
			nf.add(c2);
			nf.addWindowListener(this);
		}
	}

	void replaceall() {
		System.out.println("RplaceAll");
		str = t.getText();
		patt = tf1.getText();
		p = Pattern.compile(patt);
		m = p.matcher(str);
		if (m.find()) {
			rep = tf2.getText();
			str = str.replace(patt, rep);
			t.setText(str);
		} else {
			nf = new Frame();
			nf.setLayout(new FlowLayout());
			nf.setSize(150, 100);
			nf.setLocation(400, 400);
			nf.setVisible(true);
			nf.add(new Label("No match Found"));
			c2 = new Button("close");
			c2.addActionListener(e5 -> exit(nf));
			nf.add(c2);
			nf.addWindowListener(this);
		}
	}

	void clear() {
		t.setText("");
		name = null;
		path = null;
		f.setTitle("Untitled");
	}

	/*
	 * void replace() { if((start1 != -1 && end1 !=-1) && index!=-1) { String s
	 * = tf2.getText(); String text = t.getText(); text =
	 * text.replace("\n","\n\r"); StringBuffer s1 = new StringBuffer(text);
	 * t.setText((s1.replace(start1,end1,s)).toString()); match(); } else
	 * if(start1 == -1 && end1 == -1) { match(); } else if(index == -1) {
	 * //System.out.println("333"); frc = new Frame("Stop");
	 * frc.setSize(200,200); frc.setVisible(true); start1 = -1; end1 = -1;
	 * frc.setLayout(new GridBagLayout()); l3 = new Label("No Match Found !!");
	 * frc.add(l3); b5 = new Button("Close"); frc.add(b5);
	 * b5.addActionListener((e1)->{frc.setVisible(false);frc.dispose();});
	 * //Lambda Expression } }
	 */
	void replace() {
		int len;
		str = t.getText();
		patt = tf1.getText();
		rep = tf2.getText();

		p = Pattern.compile(patt);
		m = p.matcher(str);
		nl = 0;
		count++;
		len = rep.length();
		// for(int q=0;q<count;q++)
		if (m.find()) {
			System.out.println("m.find");
			end = m.end();
			start = m.start();
			for (i = 0; i < end; i++)
				if (str.charAt(i) == '\n')
					nl++;
			System.out.println("nl=" + nl);
			t.requestFocus();
			t.replaceText(rep, start - nl, end - nl);
		} else {
			nf = new Frame();
			nf.setLayout(new FlowLayout());
			nf.setSize(150, 100);
			nf.setLocation(400, 400);
			nf.setVisible(true);
			nf.add(new Label("No match Found"));
			c2 = new Button("close");
			c2.addActionListener(e5 -> exit(nf));
			nf.add(c2);
			nf.addWindowListener(this);
		}
	}

	String readFile(File f) throws IOException {
		FileInputStream fis = new FileInputStream(f);
		byte[] data = new byte[(int) f.length()];
		fis.read(data);
		fis.close();
		return (new String(data, "UTF-8"));
	}

	boolean isSaved() throws Exception {
		if (name == "" && (t.getText()).equals(""))
			return true;
		else {
			File file = new File(path, name);
			String s1 = t.getText();
			String s2 = readFile(file);

			if (s1.equals(s2))
				return true;
			else
				return false;
		}

	}

	void saveAs() throws IOException {
		FileDialog fd = new FileDialog(f, "Save", FileDialog.SAVE);
		fd.setVisible(true);
		name = fd.getFile();
		path = fd.getDirectory();
		if (name != null && path != null) {
			File fn = new File(path, name);
			fn.createNewFile();// duplicate file
			BufferedWriter fo = new BufferedWriter(new FileWriter(fn));
			fo.write(t.getText());
			fo.close();
			t.setText("");
			name = "";
			path = "";
			f.setTitle("Untitled");
		}
	}

	void notExist() {
		nf = new Frame();
		nf.setLayout(new FlowLayout());
		nf.setSize(150, 100);
		nf.setLocation(400, 400);
		nf.setVisible(true);
		nf.add(new Label("No file Found"));
		c2 = new Button("close");
		c2.addActionListener(e5 -> exit(nf));
		nf.add(c2);
		nf.addWindowListener(this);
	}

	void match() {
		String s = tf1.getText();
		String text = t.getText();
		text = text.replace("\n", "\n\r");
		int x = 0;
		// System.out.println(text);

		Pattern pat = Pattern.compile(s);
		Matcher m = pat.matcher(text);
		if (!m.find())
			index = -1;
		while (index != -1 && (m.start() < index)) {
			// System.out.println("111");
			if (m.find())
				System.out.println(m.start() + " " + m.end());
			else {
				index = -1;
			}
		}
		if (index != -1) {
			// System.out.println("222");
			for (int i = 0; i < m.start(); i++) {
				if (text.charAt(i) == '\n')
					x++;
			}
			t.requestFocus();
			// t.select(0,2);
			t.select(m.start() - 2 * x, m.end() - 2 * x);
			start1 = m.start();
			end1 = m.end();
			// System.out.println(start1+" "+end1);
			if ((tf2.getText()).equals(""))
				index = m.end();
			else
				index = m.start() + (tf2.getText()).length();
		} else if (frc == null) {
			System.out.println("333");
			frc = new Frame("Stop");
			frc.setSize(200, 200);
			frc.setVisible(true);
			// start = -1;
			// end = -1;
			frc.setLayout(new GridBagLayout());
			l3 = new Label("No Match Found !!");
			frc.add(l3);
			b5 = new Button("Close");
			frc.add(b5);
			b5.addActionListener((e1) -> {
				frc.setVisible(false);
				frc.dispose();
			}); // Lambda Expression
		}
	}
}