/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on JFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:
 *    AFreeChart: http://code.google.com/p/afreechart/
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * [Android is a trademark of Google Inc.]
 *
 * ----------------
 * ChartEntity.java
 * ----------------
 * 
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  shiraki  (for ICOMSYSTECH Co.,Ltd);
 * Contributor(s):   Sato Yoshiaki ;
 *                   Niwano Masayoshi;
 *
 * Changes (from 19-Nov-2010)
 * --------------------------
 * 19-Nov-2010 : port JFreeChart 1.0.13 to Android as "AFreeChart"
 * 
 * ------------- JFreeChart ---------------------------------------------
 * (C) Copyright 2002-2008, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Richard Atkinson;
 *                   Xavier Poinsard;
 *                   Robert Fuller;
 * Changes:
 * --------
 * 23-May-2002 : Version 1 (DG);
 * 12-Jun-2002 : Added Javadoc comments (DG);
 * 26-Jun-2002 : Added methods for image maps (DG);
 * 05-Aug-2002 : Added constructor and accessors for URL support in image maps
 *               Added getImageMapAreaTag() - previously in subclasses (RA);
 * 05-Sep-2002 : Added getImageMapAreaTag(boolean) to support OverLIB for
 *               tooltips http://www.bosrup.com/web/overlib (RA);
 * 03-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 08-Oct-2002 : Changed getImageMapAreaTag to use title instead of alt
 *               attribute so HTML image maps now work in Mozilla and Opera as
 *               well as Internet Explorer (RA);
 * 13-Mar-2003 : Change getImageMapAreaTag to only return a tag when there is a
 *               tooltip or URL, as suggested by Xavier Poinsard (see Feature
 *               Request 688079) (DG);
 * 12-Aug-2003 : Added support for custom image maps using
 *               ToolTipTagFragmentGenerator and URLTagFragmentGenerator (RA);
 * 02-Sep-2003 : Incorporated fix (791901) submitted by Robert Fuller (DG);
 * 19-May-2004 : Added equals() method and implemented Cloneable and
 *               Serializable (DG);
 * 29-Sep-2004 : Implemented PublicCloneable (DG);
 * 13-Jan-2005 : Fixed for compliance with XHTML 1.0 (DG);
 * 18-Apr-2005 : Use StringBuffer (DG);
 * 20-Apr-2005 : Added toString() implementation (DG);
 * ------------- JFREECHART 1.0.x ---------------------------------------------
 * 06-Feb-2007 : API doc update (DG);
 * 13-Nov-2007 : Reorganised equals(), implemented hashCode (DG);
 * 04-Dec-2007 : Added 'nohref' attribute in getImageMapAreaTag() method, to
 *               fix bug 1460195 (DG);
 * 04-Dec-2007 : Escape the toolTipText and urlText in getImageMapAreaTag() to
 *               prevent special characters corrupting the HTML (DG);
 * 05-Dec-2007 : Previous change reverted - let the tool tip and url tag
 *               generators handle filtering / escaping (DG);
 *
 */

package org.afree.chart.entity;

import java.io.Serializable;


import org.afree.util.ObjectUtilities;
import org.afree.graphics.geom.RectShape;
import org.afree.graphics.geom.Shape;

/**
 * A class that captures information about some component of a chart (a bar,
 * line etc).
 */
public class ChartEntity implements Cloneable, Serializable {

    /** For serialization. */
    private static final long serialVersionUID = -4445994133561919083L;

    /** The area occupied by the entity (in Java 2D space). */
    private transient Shape area;

    /** The tool tip text for the entity. */
    private String toolTipText;

    /** The URL text for the entity. */
    private String urlText;

    /**
     * Creates a new chart entity.
     * 
     * @param area
     *            the area (<code>null</code> not permitted).
     */
    public ChartEntity(Shape area) {
        // defer argument checks...
        this(area, null);
    }

    /**
     * Creates a new chart entity.
     * 
     * @param area
     *            the area (<code>null</code> not permitted).
     * @param toolTipText
     *            the tool tip text (<code>null</code> permitted).
     */
    public ChartEntity(Shape area, String toolTipText) {
        // defer argument checks...
        this(area, toolTipText, null);
    }

    /**
     * Creates a new entity.
     * 
     * @param area
     *            the area (<code>null</code> not permitted).
     * @param toolTipText
     *            the tool tip text (<code>null</code> permitted).
     * @param urlText
     *            the URL text for HTML image maps (<code>null</code>
     *            permitted).
     */
    public ChartEntity(Shape area, String toolTipText, String urlText) {
        if (area == null) {
            throw new IllegalArgumentException("Null 'area' argument.");
        }
        this.area = area;
        this.toolTipText = toolTipText;
        this.urlText = urlText;
    }

    /**
     * Returns the area occupied by the entity (in Java 2D space).
     * 
     * @return The area (never <code>null</code>).
     */
    public Shape getArea() {
        return this.area;
    }

    /**
     * Sets the area for the entity.
     * <P>
     * This class conveys information about chart entities back to a client.
     * Setting this area doesn't change the entity (which has already been
     * drawn).
     * 
     * @param area
     *            the area (<code>null</code> not permitted).
     */
    public void setArea(Shape area) {
        if (area == null) {
            throw new IllegalArgumentException("Null 'area' argument.");
        }
        this.area = area;
    }

    /**
     * Returns the tool tip text for the entity. Be aware that this text may
     * have been generated from user supplied data, so for security reasons some
     * form of filtering should be applied before incorporating this text into
     * any HTML output.
     * 
     * @return The tool tip text (possibly <code>null</code>).
     */
    public String getToolTipText() {
        return this.toolTipText;
    }

    /**
     * Sets the tool tip text.
     * 
     * @param text
     *            the text (<code>null</code> permitted).
     */
    public void setToolTipText(String text) {
        this.toolTipText = text;
    }

    /**
     * Returns the URL text for the entity. Be aware that this text may have
     * been generated from user supplied data, so some form of filtering should
     * be applied before this "URL" is used in any output.
     * 
     * @return The URL text (possibly <code>null</code>).
     */
    public String getURLText() {
        return this.urlText;
    }

    /**
     * Sets the URL text.
     * 
     * @param text
     *            the text (<code>null</code> permitted).
     */
    public void setURLText(String text) {
        this.urlText = text;
    }

    /**
     * Returns a string describing the entity area. This string is intended for
     * use in an AREA tag when generating an image map.
     * 
     * @return The shape type (never <code>null</code>).
     */
    public String getShapeType() {
        if (this.area instanceof RectShape) {
            return "rect";
        } else {
            return "poly";
        }
    }

    /**
     * Returns the shape coordinates as a string.
     * 
     * @return The shape coordinates (never <code>null</code>).
     */
    public String getShapeCoords() {
        if (this.area instanceof RectShape) {
            return getRectCoords((RectShape) this.area);
        } else {
            return getPolyCoords(this.area);
        }
    }

    /**
     * Returns a string containing the coordinates (x1, y1, x2, y2) for a given
     * RectShape. This string is intended for use in an image map.
     * 
     * @param RectShape
     *            the RectShape (<code>null</code> not permitted).
     * 
     * @return Upper left and lower right corner of a RectShape.
     */
    private String getRectCoords(RectShape RectShape) {
        if (RectShape == null) {
            throw new IllegalArgumentException("Null 'RectShape' argument.");
        }
        int x1 = (int) RectShape.getX();
        int y1 = (int) RectShape.getY();
        int x2 = x1 + (int) RectShape.getWidth();
        int y2 = y1 + (int) RectShape.getHeight();
        // fix by rfuller
        if (x2 == x1) {
            x2++;
        }
        if (y2 == y1) {
            y2++;
        }
        // end fix by rfuller
        return x1 + "," + y1 + "," + x2 + "," + y2;
    }

    /**
     * Returns a string containing the coordinates for a given shape. This
     * string is intended for use in an image map.
     * 
     * @param shape
     *            the shape (<code>null</code> not permitted).
     * 
     * @return The coordinates for a given shape as string.
     */
    private String getPolyCoords(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Null 'shape' argument.");
        }
        StringBuffer result = new StringBuffer();
        // TODO: implement
        return result.toString();
    }

    /**
     * Returns a string representation of the chart entity, useful for
     * debugging.
     * 
     * @return A string.
     */
    public String toString() {
        StringBuffer buf = new StringBuffer("ChartEntity: ");
        buf.append("tooltip = ");
        buf.append(this.toolTipText);
        return buf.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ChartEntity)) {
            return false;
        }
        ChartEntity that = (ChartEntity) obj;
        if (!this.area.equals(that.area)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.toolTipText, that.toolTipText)) {
            return false;
        }
        if (!ObjectUtilities.equal(this.urlText, that.urlText)) {
            return false;
        }
        return true;
    }    
    
    /**
     * Returns a clone of the entity.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException if there is a problem cloning the
     *         entity.
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }    
    
}
