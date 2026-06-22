/**
 * TicketSystemGUI - Main GUI window for the SupportDesk Simulator.
 * Phases 4-10: Static containers, backend wiring, error handling, menu,
 * custom buttons, social links, info button, splash screen.
 */
package ticketsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URI;

public class TicketSystemGUI extends JFrame {

    // --- Backend Data Structures ---
    private TicketQueue ticketQueue;
    private ResolvedStack resolvedStack;

    // --- Queue Display (Phase 4) ---
    private JLabel[] queueBoxes;
    private JLabel queueCapacityLabel, queueIsFullLabel, queueIsEmptyLabel;
    private JLabel queueFrontLabel, queueRearLabel;

    // --- Stack Display (Phase 4) ---
    private JLabel[] stackBoxes;
    private JLabel stackCapacityLabel, stackIsFullLabel, stackIsEmptyLabel;
    private JLabel stackTopLabel;

    // --- Input & Actions (Phase 5) ---
    private JTextField inputField;
    private JLabel errorLabel;
    private JLabel lastActionLabel;

    // --- Custom Button Components (Phase 8) ---
    private JLabel addTicketBtn, processTicketBtn, reopenTicketBtn, clearActionBtn;
    private JLabel menuBtn, closeMenuBtn, showQueueBtn, showStackBtn;
    private JLabel infoBtn;

    // --- Social Media Icons (Phase 9) ---
    private JLabel youtubeIcon, facebookIcon, twitterIcon, hamdardIcon;

    // --- Menu Panel (Phase 7) ---
    private JPanel slideMenu;

    // --- View State ---
    private boolean showingQueue = true;

    // --- Color Palette ---
    private static final Color BOX_BORDER = new Color(70, 130, 200);
    private static final Color BOX_FILL_EMPTY = new Color(30, 40, 60);
    private static final Color BOX_FILL_FILLED = new Color(50, 120, 200);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color ACCENT_COLOR = new Color(100, 180, 255);
    private static final Color MENU_BG = new Color(20, 28, 48, 230);
    private static final Color BUTTON_HOVER = new Color(60, 140, 220);

    public TicketSystemGUI() {
        // Initialize data structures
        ticketQueue = new TicketQueue(6);
        resolvedStack = new ResolvedStack(6);

        // Frame setup (Phase 3)
        setTitle("SupportDesk Simulator | Stack & Queue");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Set window icon
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/icon.png"));
            setIconImage(icon.getImage());
        } catch (Exception e) {
            BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            g2d.setColor(ACCENT_COLOR);
            g2d.fillRect(0, 0, 32, 32);
            g2d.dispose();
            setIconImage(img);
        }

        // Main panel with gradient background
        JPanel mainPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(15, 20, 35),
                        0, getHeight(), new Color(25, 35, 60));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);
        setContentPane(mainPanel);

        // Build all UI components (Phases 4-9)
        initQueueBoxes(mainPanel);
        initStackBoxes(mainPanel);
        initQueueInfoLabels(mainPanel);
        initStackInfoLabels(mainPanel);
        initInputSection(mainPanel);
        initActionButtons(mainPanel);
        initErrorAndActionLabels(mainPanel);
        initMenuPanel(mainPanel);
        initSocialIcons(mainPanel);
        initInfoButton(mainPanel);
        initSectionHeaders(mainPanel);

        // Initial display refresh
        refreshQueueDisplay();
        refreshStackDisplay();
    }

    // ======================== PHASE 4: STATIC VISUAL CONTAINERS ========================

    private void initQueueBoxes(JPanel panel) {
        queueBoxes = new JLabel[6];
        int boxSize = 140;
        int startX = 90;
        int y = 260;
        int gap = 20;
        for (int i = 0; i < 6; i++) {
            queueBoxes[i] = createBox("", startX + i * (boxSize + gap), y, boxSize, 60);
            panel.add(queueBoxes[i]);
        }
    }

    private void initStackBoxes(JPanel panel) {
        stackBoxes = new JLabel[6];
        int boxSize = 140;
        int startX = 90;
        int y = 260;
        int gap = 20;
        for (int i = 0; i < 6; i++) {
            stackBoxes[i] = createBox("", startX + i * (boxSize + gap), y, boxSize, 60);
            stackBoxes[i].setVisible(false);
            panel.add(stackBoxes[i]);
        }
    }

    private JLabel createBox(String text, int x, int y, int w, int h) {
        JLabel box = new JLabel(text, SwingConstants.CENTER);
        box.setBounds(x, y, w, h);
        box.setOpaque(true);
        box.setBackground(BOX_FILL_EMPTY);
        box.setForeground(TEXT_COLOR);
        box.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        box.setBorder(BorderFactory.createLineBorder(BOX_BORDER, 2, true));
        return box;
    }

    private void initQueueInfoLabels(JPanel panel) {
        int labelY = 340;
        int startX = 90;
        int labelW = 160;
        Font infoFont = new Font("Segoe UI", Font.BOLD, 13);
        queueCapacityLabel = createInfoLabel("Capacity: 6", startX, labelY, labelW, 25, infoFont);
        queueIsFullLabel = createInfoLabel("isFull: false", startX + 170, labelY, labelW, 25, infoFont);
        queueIsEmptyLabel = createInfoLabel("isEmpty: true", startX + 340, labelY, labelW, 25, infoFont);
        queueFrontLabel = createInfoLabel("Front: -1", startX + 510, labelY, labelW, 25, infoFont);
        queueRearLabel = createInfoLabel("Rear: -1", startX + 680, labelY, labelW, 25, infoFont);
        panel.add(queueCapacityLabel);
        panel.add(queueIsFullLabel);
        panel.add(queueIsEmptyLabel);
        panel.add(queueFrontLabel);
        panel.add(queueRearLabel);
    }

    private void initStackInfoLabels(JPanel panel) {
        int labelY = 340;
        int startX = 90;
        int labelW = 160;
        Font infoFont = new Font("Segoe UI", Font.BOLD, 13);
        stackCapacityLabel = createInfoLabel("Capacity: 6", startX, labelY, labelW, 25, infoFont);
        stackCapacityLabel.setVisible(false);
        stackIsFullLabel = createInfoLabel("isFull: false", startX + 170, labelY, labelW, 25, infoFont);
        stackIsFullLabel.setVisible(false);
        stackIsEmptyLabel = createInfoLabel("isEmpty: true", startX + 340, labelY, labelW, 25, infoFont);
        stackIsEmptyLabel.setVisible(false);
        stackTopLabel = createInfoLabel("Top: -1", startX + 510, labelY, labelW, 25, infoFont);
        stackTopLabel.setVisible(false);
        panel.add(stackCapacityLabel);
        panel.add(stackIsFullLabel);
        panel.add(stackIsEmptyLabel);
        panel.add(stackTopLabel);
    }

    private JLabel createInfoLabel(String text, int x, int y, int w, int h, Font font) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setForeground(ACCENT_COLOR);
        label.setFont(font);
        return label;
    }

    private void initSectionHeaders(JPanel panel) {
        JLabel queueHeader = new JLabel("PENDING QUEUE", SwingConstants.CENTER);
        queueHeader.setBounds(90, 220, 940, 30);
        queueHeader.setForeground(ACCENT_COLOR);
        queueHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        queueHeader.setName("queueHeader");
        panel.add(queueHeader);

        JLabel stackHeader = new JLabel("RESOLVED STACK", SwingConstants.CENTER);
        stackHeader.setBounds(90, 220, 940, 30);
        stackHeader.setForeground(ACCENT_COLOR);
        stackHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        stackHeader.setVisible(false);
        stackHeader.setName("stackHeader");
        panel.add(stackHeader);
    }

    // ======================== PHASE 5: INPUT & BUTTONS ========================

    private void initInputSection(JPanel panel) {
        JLabel inputLabel = new JLabel("Describe the issue:");
        inputLabel.setBounds(90, 400, 200, 25);
        inputLabel.setForeground(TEXT_COLOR);
        inputLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(inputLabel);

        inputField = new JTextField();
        inputField.setBounds(90, 430, 450, 35);
        inputField.setBackground(new Color(30, 40, 60));
        inputField.setForeground(TEXT_COLOR);
        inputField.setCaretColor(TEXT_COLOR);
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BOX_BORDER, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputField.addActionListener(e -> addTicket());
        panel.add(inputField);
    }

    private void initActionButtons(JPanel panel) {
        int btnY = 480;
        int btnW = 130;
        int btnH = 40;
        addTicketBtn = createCustomButton("Add Ticket", 90, btnY, btnW, btnH, () -> addTicket());
        processTicketBtn = createCustomButton("Process Ticket", 240, btnY, btnW, btnH, () -> processTicket());
        reopenTicketBtn = createCustomButton("Reopen Ticket", 390, btnY, btnW, btnH, () -> reopenTicket());
        clearActionBtn = createCustomButton("Clear Last Action", 540, btnY, btnW + 20, btnH, () -> clearLastAction());
        panel.add(addTicketBtn);
        panel.add(processTicketBtn);
        panel.add(reopenTicketBtn);
        panel.add(clearActionBtn);
        menuBtn = createCustomButton("☰ Menu", 20, 20, 80, 35, () -> toggleMenu());
        panel.add(menuBtn);
    }

    // ======================== PHASE 8: CUSTOM BUTTONS & HOVER EFFECTS ========================

    private JLabel createCustomButton(String text, int x, int y, int w, int h, Runnable action) {
        JLabel btn = new JLabel(text, SwingConstants.CENTER);
        btn.setBounds(x, y, w, h);
        btn.setOpaque(true);
        btn.setBackground(new Color(40, 60, 90));
        btn.setForeground(TEXT_COLOR);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BOX_BORDER, 1, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { btn.setBackground(BUTTON_HOVER); }
            @Override
            public void mouseExited(MouseEvent e) { btn.setBackground(new Color(40, 60, 90)); }
            @Override
            public void mousePressed(MouseEvent e) { btn.setBackground(new Color(30, 80, 140)); }
            @Override
            public void mouseReleased(MouseEvent e) {
                btn.setBackground(BUTTON_HOVER);
                if (btn.contains(e.getPoint())) action.run();
            }
        });
        return btn;
    }

    // ======================== PHASE 6: ERROR HANDLING ========================

    private void initErrorAndActionLabels(JPanel panel) {
        errorLabel = new JLabel("");
        errorLabel.setBounds(90, 530, 800, 25);
        errorLabel.setForeground(new Color(255, 80, 80));
        errorLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(errorLabel);

        lastActionLabel = new JLabel("");
        lastActionLabel.setBounds(90, 560, 800, 25);
        lastActionLabel.setForeground(new Color(150, 200, 255));
        lastActionLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        panel.add(lastActionLabel);

        JLabel titleLabel = new JLabel("SupportDesk Simulator", SwingConstants.CENTER);
        titleLabel.setBounds(0, 50, 1200, 50);
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        panel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Customer Support Ticket System | Queue + Stack", SwingConstants.CENTER);
        subtitleLabel.setBounds(0, 100, 1200, 30);
        subtitleLabel.setForeground(ACCENT_COLOR);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panel.add(subtitleLabel);
    }

    // ======================== PHASE 7: MENU & VIEW SWITCHING ========================

    private void initMenuPanel(JPanel panel) {
        slideMenu = new JPanel(null);
        slideMenu.setBounds(-250, 0, 250, 700);
        slideMenu.setBackground(MENU_BG);
        slideMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, BOX_BORDER));

        JLabel menuTitle = new JLabel("MENU", SwingConstants.CENTER);
        menuTitle.setBounds(0, 30, 250, 40);
        menuTitle.setForeground(ACCENT_COLOR);
        menuTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        slideMenu.add(menuTitle);

        closeMenuBtn = createCustomButton("✕", 200, 20, 35, 35, this::toggleMenu);
        slideMenu.add(closeMenuBtn);

        showQueueBtn = createCustomButton("Show Pending Queue", 25, 120, 200, 45, () -> {
            showQueueView();
            toggleMenu();
        });
        slideMenu.add(showQueueBtn);

        showStackBtn = createCustomButton("Show Resolved Stack", 25, 180, 200, 45, () -> {
            showStackView();
            toggleMenu();
        });
        slideMenu.add(showStackBtn);

        slideMenu.setVisible(true);
        panel.add(slideMenu);
    }

    // ======================== PHASE 9: SOCIAL LINKS & INFO BUTTON ========================

    private void initSocialIcons(JPanel panel) {
        int y = 620;
        int startX = 90;
        int iconSize = 30;
        int gap = 10;
        youtubeIcon = createSocialIcon("YouTube", startX, y, iconSize, iconSize, "https://youtube.com");
        facebookIcon = createSocialIcon("Facebook", startX + iconSize + gap, y, iconSize, iconSize, "https://facebook.com");
        twitterIcon = createSocialIcon("Twitter", startX + 2 * (iconSize + gap), y, iconSize, iconSize, "https://twitter.com");
        hamdardIcon = createSocialIcon("HU", startX + 3 * (iconSize + gap), y, iconSize, iconSize, "https://hamdard.edu.pk");
        panel.add(youtubeIcon);
        panel.add(facebookIcon);
        panel.add(twitterIcon);
        panel.add(hamdardIcon);
        JLabel socialLabel = new JLabel("Follow us:");
        socialLabel.setBounds(startX - 80, y, 80, iconSize);
        socialLabel.setForeground(ACCENT_COLOR);
        socialLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(socialLabel);
    }

    private JLabel createSocialIcon(String text, int x, int y, int w, int h, String url) {
        JLabel icon = new JLabel(text, SwingConstants.CENTER);
        icon.setBounds(x, y, w, h);
        icon.setOpaque(true);
        icon.setBackground(new Color(40, 60, 90));
        icon.setForeground(ACCENT_COLOR);
        icon.setFont(new Font("Segoe UI", Font.BOLD, 10));
        icon.setBorder(BorderFactory.createLineBorder(BOX_BORDER, 1, true));
        icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { icon.setBackground(BUTTON_HOVER); }
            @Override
            public void mouseExited(MouseEvent e) { icon.setBackground(new Color(40, 60, 90)); }
            @Override
            public void mouseClicked(MouseEvent e) { openWebPage(url); }
        });
        return icon;
    }

    private void initInfoButton(JPanel panel) {
        infoBtn = new JLabel("Info", SwingConstants.CENTER);
        infoBtn.setBounds(1060, 20, 80, 35);
        infoBtn.setOpaque(true);
        infoBtn.setBackground(new Color(40, 60, 90));
        infoBtn.setForeground(TEXT_COLOR);
        infoBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        infoBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BOX_BORDER, 1, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        infoBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel infoDisplay = new JLabel("");
        infoDisplay.setBounds(850, 20, 200, 35);
        infoDisplay.setForeground(new Color(255, 220, 100));
        infoDisplay.setFont(new Font("Segoe UI", Font.BOLD, 13));

        infoBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { infoBtn.setBackground(BUTTON_HOVER); }
            @Override
            public void mouseExited(MouseEvent e) { infoBtn.setBackground(new Color(40, 60, 90)); }
            @Override
            public void mousePressed(MouseEvent e) {
                infoBtn.setBackground(new Color(30, 80, 140));
                infoDisplay.setText("Developed by Student | CMS: 123456");
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                infoBtn.setBackground(BUTTON_HOVER);
                infoDisplay.setText("");
            }
        });
        panel.add(infoBtn);
        panel.add(infoDisplay);
    }

    // ======================== PHASE 5: CORE ACTIONS ========================

    private void addTicket() {
        String ticket = inputField.getText().trim();
        if (ticket.isEmpty()) {
            errorLabel.setText("Please describe the issue before adding a ticket.");
            return;
        }
        if (ticketQueue.isFull()) {
            errorLabel.setText("Queue is full! Cannot add more tickets.");
            return;
        }
        ticketQueue.enqueue(ticket);
        inputField.setText("");
        errorLabel.setText("");
        lastActionLabel.setText("Last Action: Added \"" + ticket + "\" to queue");
        refreshQueueDisplay();
    }

    private void processTicket() {
        if (ticketQueue.isEmpty()) {
            errorLabel.setText("Queue is empty! No tickets to process.");
            return;
        }
        if (resolvedStack.isFull()) {
            errorLabel.setText("Resolved stack is full! Reopen a ticket before processing more.");
            return;
        }
        String ticket = ticketQueue.dequeue();
        resolvedStack.push(ticket);
        errorLabel.setText("");
        lastActionLabel.setText("Last Action: Processed \"" + ticket + "\" → moved to resolved stack");
        refreshQueueDisplay();
        refreshStackDisplay();
    }

    private void reopenTicket() {
        if (resolvedStack.isEmpty()) {
            errorLabel.setText("Resolved stack is empty! No tickets to reopen.");
            return;
        }
        if (ticketQueue.isFull()) {
            errorLabel.setText("Queue is full! Process a ticket before reopening.");
            return;
        }
        String ticket = resolvedStack.pop();
        ticketQueue.enqueue(ticket);
        errorLabel.setText("");
        lastActionLabel.setText("Last Action: Reopened \"" + ticket + "\" → moved back to queue");
        refreshQueueDisplay();
        refreshStackDisplay();
    }

    private void clearLastAction() {
        lastActionLabel.setText("");
        errorLabel.setText("");
    }

    // ======================== DISPLAY REFRESH ========================

    private void refreshQueueDisplay() {
        for (int i = 0; i < 6; i++) {
            String content = ticketQueue.get(i);
            if (content != null) {
                queueBoxes[i].setText(truncateText(content, 12));
                queueBoxes[i].setBackground(BOX_FILL_FILLED);
            } else {
                queueBoxes[i].setText("[Empty]");
                queueBoxes[i].setBackground(BOX_FILL_EMPTY);
            }
        }
        queueIsFullLabel.setText("isFull: " + ticketQueue.isFull());
        queueIsEmptyLabel.setText("isEmpty: " + ticketQueue.isEmpty());
        queueFrontLabel.setText("Front: " + ticketQueue.getFront());
        queueRearLabel.setText("Rear: " + ticketQueue.getRear());
    }

    private void refreshStackDisplay() {
        for (int i = 0; i < 6; i++) {
            String content = resolvedStack.get(i);
            if (content != null) {
                stackBoxes[i].setText(truncateText(content, 12));
                stackBoxes[i].setBackground(BOX_FILL_FILLED);
            } else {
                stackBoxes[i].setText("[Empty]");
                stackBoxes[i].setBackground(BOX_FILL_EMPTY);
            }
        }
        stackIsFullLabel.setText("isFull: " + resolvedStack.isFull());
        stackIsEmptyLabel.setText("isEmpty: " + resolvedStack.isEmpty());
        stackTopLabel.setText("Top: " + resolvedStack.getTop());
    }

    private String truncateText(String text, int maxLen) {
        if (text.length() > maxLen) {
            return "<html>" + text.substring(0, maxLen) + "...</html>";
        }
        return text;
    }

    // ======================== PHASE 7: VIEW SWITCHING ========================

    private void toggleMenu() {
        int targetX = slideMenu.getX() < -100 ? 0 : -250;
        slideMenu.setLocation(targetX, 0);
    }

    private void showQueueView() {
        showingQueue = true;
        for (JLabel box : queueBoxes) box.setVisible(true);
        for (JLabel box : stackBoxes) box.setVisible(false);
        queueCapacityLabel.setVisible(true);
        queueIsFullLabel.setVisible(true);
        queueIsEmptyLabel.setVisible(true);
        queueFrontLabel.setVisible(true);
        queueRearLabel.setVisible(true);
        stackCapacityLabel.setVisible(false);
        stackIsFullLabel.setVisible(false);
        stackIsEmptyLabel.setVisible(false);
        stackTopLabel.setVisible(false);
        Component[] comps = getContentPane().getComponents();
        for (Component c : comps) {
            if (c instanceof JLabel) {
                if ("queueHeader".equals(c.getName())) c.setVisible(true);
                else if ("stackHeader".equals(c.getName())) c.setVisible(false);
            }
        }
    }

    private void showStackView() {
        showingQueue = false;
        for (JLabel box : queueBoxes) box.setVisible(false);
        for (JLabel box : stackBoxes) box.setVisible(true);
        queueCapacityLabel.setVisible(false);
        queueIsFullLabel.setVisible(false);
        queueIsEmptyLabel.setVisible(false);
        queueFrontLabel.setVisible(false);
        queueRearLabel.setVisible(false);
        stackCapacityLabel.setVisible(true);
        stackIsFullLabel.setVisible(true);
        stackIsEmptyLabel.setVisible(true);
        stackTopLabel.setVisible(true);
        Component[] comps = getContentPane().getComponents();
        for (Component c : comps) {
            if (c instanceof JLabel) {
                if ("queueHeader".equals(c.getName())) c.setVisible(false);
                else if ("stackHeader".equals(c.getName())) c.setVisible(true);
            }
        }
    }

    // ======================== UTILITY ========================

    private void openWebPage(String url) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                errorLabel.setText("Cannot open browser on this system.");
            }
        } catch (Exception e) {
            errorLabel.setText("Error opening link: " + e.getMessage());
        }
    }

    // ======================== PHASE 10: SPLASH SCREEN ========================

    public static void main(String[] args) {
        showSplashScreen();
    }

    private static void showSplashScreen() {
        JWindow splash = new JWindow();
        splash.setSize(500, 300);
        splash.setLocationRelativeTo(null);

        JPanel splashPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(15, 20, 35),
                        0, getHeight(), new Color(25, 35, 60));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                try {
                    ImageIcon bgIcon = new ImageIcon(TicketSystemGUI.class.getResource("/resources/bg.jpg"));
                    g2d.drawImage(bgIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
                } catch (Exception e) { /* fallback to gradient */ }
            }
        };
        splash.setContentPane(splashPanel);

        JLabel splashTitle = new JLabel("SupportDesk Simulator", SwingConstants.CENTER);
        splashTitle.setBounds(0, 80, 500, 50);
        splashTitle.setForeground(Color.WHITE);
        splashTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
        splashPanel.add(splashTitle);

        JLabel splashSub = new JLabel("Customer Support Ticket System", SwingConstants.CENTER);
        splashSub.setBounds(0, 130, 500, 30);
        splashSub.setForeground(new Color(100, 180, 255));
        splashSub.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        splashPanel.add(splashSub);

        JLabel loading = new JLabel("Loading...", SwingConstants.CENTER);
        loading.setBounds(0, 200, 500, 30);
        loading.setForeground(new Color(150, 150, 150));
        loading.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        splashPanel.add(loading);

        splash.setVisible(true);

        Timer timer = new Timer(3000, e -> {
            splash.setVisible(false);
            splash.dispose();
            SwingUtilities.invokeLater(() -> {
                TicketSystemGUI gui = new TicketSystemGUI();
                gui.setVisible(true);
            });
        });
        timer.setRepeats(false);
        timer.start();
    }
}