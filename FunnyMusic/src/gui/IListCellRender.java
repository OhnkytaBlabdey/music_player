package gui;

import java.awt.Color;

import java.awt.Component;

import java.awt.Graphics;

import javax.swing.Icon;

import javax.swing.JLabel;

import javax.swing.JList;

import javax.swing.ListCellRenderer;


public class IListCellRender extends JLabel implements ListCellRenderer{
	
	private static final long serialVersionUID = 1L;

	private boolean m_selected = false;

	@Override
	public Component getListCellRendererComponent(JList list, Object value,

			int index, boolean isSelected, boolean cellHasFocus) {

		if(value instanceof IListItemData){

			IListItemData data = (IListItemData)value;

			setIcon(data.getLabelImage());

			setText(data.getLabelName());

		}else{

			setIcon(null);

			setText("");

		}

		setBackground(Color.WHITE);
		
		m_selected = isSelected;

		if(isSelected){

			setBackground(Color.CYAN);

		}

		return this;

	}

	@Override
	public void paintComponent(Graphics g) {

		Color bColor = getBackground();

		Icon icon = getIcon();

		g.setColor(bColor);

		int offset = 0;

		if (icon != null && getText() != null)

			offset = (icon.getIconWidth() + getIconTextGap());

		g.fillRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1);

		if (m_selected) {

			g.setColor(Color.red);

			g.draw3DRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1, true);

		}

		super.paintComponent(g);

	}

}
